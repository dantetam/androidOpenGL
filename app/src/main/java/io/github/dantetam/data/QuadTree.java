package io.github.dantetam.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dante Tam
 *
 * A utility QuadTree class used for efficiently loading chunks, and finding objects within them
 */

public abstract class QuadTree<K, V> {

    public Node<K, V> root;

    public abstract Node group(K[][] keys, Node[][] data);
    protected int len(int n, int mod) {
        int extra = n%mod > 0 ? 1 : 0;
        return n/mod + extra;
    }
    protected boolean inBounds(Object[][] table, double r, double c) {
        if (table.length == 0) {
            throw new IllegalArgumentException("Degenerate table");
        }
        return r >= 0 && c >= 0 && r < table.length && c < table[0].length;
    }

    public abstract K combine(K[] data);

    //public abstract V search(K key);
    public V search(K key) {
        Node<K,V> node = traverseForNode(key);
        return node == null ? null : node.data;
    }
    public abstract Node traverseForNode(K key);

    /*public int[] combine(int[][] data) {
        double[] temp = {0,0}
        for (int i = 0; i < data.length; i++) {

        }
    }*/

    public class Node<A, B> {
        public A key;
        public B data;
        public Node[] children = null;
        public Node(A key, Node[] c) {
            this.key = key;
            children = c;
        }
        public Node(A key, List<Node> c) {
            this.key = key;
            children = new Node[c.size()];
            for (int i = 0; i < c.size(); i++) {
                children[i] = c.get(i);
            }
        }
        public Node(A key, B d) {
            this.key = key;
            data = d;
        }
    }

}