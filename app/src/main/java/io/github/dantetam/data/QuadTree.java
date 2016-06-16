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
            for (int i = 0; i < newKeys.length; i++) {
                for (int j = 0; j < newKeys[0].length/2; j++) {
                    ArrayList<K> neighbors = new ArrayList<K>();
                    int[][] localKeys = new int[][]{
                            new int[]{i * 2, j * 2},
                            new int[]{i * 2, j * 2 + 1},
                            new int[]{i * 2 + 1, j * 2},
                            new int[]{i * 2 + 1, j * 2 + 1}
                    };
                    //keys[i*2][j*2], keys[i*2][j*2 + 1], keys[i*2 + 1][j*2], keys[i*2 + 1][j*2 + 1]
                    for (int k = 0; k < localKeys.length; k++) {
                        if (inBounds(keys, localKeys[i][0], localKeys[i][1])) {
                            neighbors.add(keys[localKeys[i][0]][localKeys[i][1]]);
                        }
                    }
                    if (inBounds(keys, i*2, j*2)) neighbors.add(keys[i*2][j*2]);
                    if (inBounds(keys, i*2, j*2 + 1)) neighbors.add(keys[i*2][j*2 + 1]);
                    if (inBounds(keys, i*2 + 1, j*2)) neighbors.add(keys[i*2 + 1][j*2]);
                    if (inBounds(keys, i*2 + 1, j*2 + 1)) neighbors.add(keys[i*2 + 1][j*2 + 1]);
                    newKeys[i][j] = combine(localKeys);
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
    private boolean inBounds(Object[][] table, int r, int c) {
        if (table.length == 0) {
            throw new IllegalArgumentException("Degenerate table");
        }
        return r >= 0 && c >= 0 && r < table.length && c < table[0].length;
    }

    public abstract K combine(List<K> data);

    /*public int[] combine(int[][] data) {
        double[] temp = {0,0}
        for (int i = 0; i < data.length; i++) {

        }
    }*/

    public class Node<A, B> {
        public B data;
        public Node[] children;
        public Node(Node[] c) {
            children = c;
        }
        public Node(List<Node> c) {
            children = new Node[c.size()];
            for (int i = 0; i < c.size(); i++) {
                children[i] = c.get(i);
            }
        }
        public Node(B d) {
            data = d;
        }
    }

}