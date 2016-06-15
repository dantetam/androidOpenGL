package io.github.dantetam.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dante Tam
 *
 * A utility QuadTree class used for efficiently loading chunks, and finding objects within them
 */

public class QuadTree<T> {

    private Node root;

    public QuadTree(List<T> data, TwoWayComparator k, int levels) {
        this(data, new Node(k), levels);
    }

    private QuadTree(List<T> data, Node r, int levels) {
        root = r;
        root.createChildren(levels - 1);
        for (T t: data) {
            root.add(t, t.key());
        }
    }
	
	/*public static Node getNewRoot(Key k) {
		return new Node(k);
	}*/

    //Do not expose Node intrinsics to usable QuadTree class
    private static class Node<T, K extends TwoWayComparator<K>> {
        public List<T> items;
        private Node[] children;
        public K key;

        public Node(K k) {
            key = k;
            items = new ArrayList<T>();
            children = null;
        }

        public void createChildren(int levelsBelow) {
            if (levelsBelow <= 0) return;
            K[] keys = key.createChildren();
            children = new Node[keys.length];
            for (int i = 0; i < keys.length; i++) {
                children[i] = new Node(keys[i]);
                createChildren(levelsBelow - 1);
            }
        }

        public void add(T item, K k) {
            Node n = traverse(k);
            if (n != null) n.items.add(item);
        }

        public List<T> get(K k) {
            Node n = traverse(k);
            return n == null ? null : n.items;
        }

        public List<T> getAbsolute(K k) {
            Node n = traverse(k);
            if (n == null) return null;
            if (n.key.equals(k)) return n.items;
            return null;
        }

        public Node traverse(K k) {
            if (key.equals(k) || children == null) return this;
            int nextChildIndex = key.getCompareScores(key, k);
            return children[nextChildIndex].traverse(k);
        }
    }

}