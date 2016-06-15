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

    public Node group(K[][] keys, Node[][] data) {
        while (true) {
            K[][] newKeys = (K[][]) new Object[len(keys.length, 2)][len(keys[0].length, 2)];
            Node[][] newData = (Node[][]) new Object[len(data.length, 2)][len(data[0].length, 2)];
            for (int i = 0; i < keys.length/2; i++) {
                for (int j = 0; j < keys[0].length/2; j++) {
                    K[] neighbors = (K[]) new Object[]{keys[i*2][j*2], keys[i*2][j*2 + 1], keys[i*2 + 1][j*2], keys[i*2 + 1][j*2 + 1]};
                    newKeys[i][j] = combine(neighbors);
                    newData[i][j] = new Node(neighbors);
                }
            }
            keys = newKeys;
            data = newData;
            if (keys.length == 1 && keys[0].length == 1) {
                return data[0][0];
            }
        }
    }
    private int len(int n, int mod) {
        int extra = n%mod > 0 ? 1 : 0;
        return n/mod + extra;
    }

    public abstract K combine(K[] data);

    public class Node<A, B> {
        public B data;
        public Node[] children;
        public Node(Node[] c) {
            children = c;
        }
        public Node(B d) {
            data = d;
        }
    }

}