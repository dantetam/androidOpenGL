package io.github.dantetam.data;

import java.util.ArrayList;
import java.util.List;

import io.github.dantetam.world.Tile;

/**
 * Created by Dante on 6/16/2016.
 */
public class WorldTree extends QuadTree<double[], Tile>{

    public WorldTree() {

    }

    public void init(Tile[][] tiles) {
        Node[][] nodes = new Node[tiles.length][tiles[0].length];
        double[][][] rowCol = new double[tiles.length][tiles[0].length][];
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[0].length; c++) {
                nodes[r][c] = new Node(new double[]{r,c}, tiles[r][c]);
                rowCol[r][c] = new double[]{r,c};
            }
        }
        root = group(rowCol, nodes);
    }

    @Override
    public Node group(double[][][] keys, Node[][] data) {
        while (true) {
            double[][][] newKeys = new double[len(keys.length, 2)][len(keys[0].length, 2)][];
            Node[][] newData = (Node[][]) new Object[len(data.length, 2)][len(data[0].length, 2)];
            for (int i = 0; i < newKeys.length; i++) {
                for (int j = 0; j < newKeys[0].length; j++) {
                    ArrayList<double[]> neighbors = new ArrayList<double[]>();
                    double[][] localKeys = new double[][]{
                            new double[]{i * 2, j * 2},
                            new double[]{i * 2 + 1, j * 2},
                            new double[]{i * 2, j * 2 + 1},
                            new double[]{i * 2 + 1, j * 2 + 1}
                    };
                    //keys[i*2][j*2], keys[i*2][j*2 + 1], keys[i*2 + 1][j*2], keys[i*2 + 1][j*2 + 1]
                    for (int k = 0; k < localKeys.length; k++) {
                        if (inBounds(keys, localKeys[i][0], localKeys[i][1])) {
                            neighbors.add(keys[(int)localKeys[i][0]][(int)localKeys[i][1]]);
                        }
                    }
                    /*if (inBounds(keys, i*2, j*2)) neighbors.add(keys[i*2][j*2]);
                    if (inBounds(keys, i*2, j*2 + 1)) neighbors.add(keys[i*2][j*2 + 1]);
                    if (inBounds(keys, i*2 + 1, j*2)) neighbors.add(keys[i*2 + 1][j*2]);
                    if (inBounds(keys, i*2 + 1, j*2 + 1)) neighbors.add(keys[i*2 + 1][j*2 + 1]);*/
                    newKeys[i][j] = combine(localKeys);
                    newData[i][j] = new Node(newKeys[i][j], neighbors);
                }
            }
            keys = newKeys;
            data = newData;
            if (keys.length == 1 && keys[0].length == 1) {
                return data[0][0];
            }
        }
    }

    @Override
    public double[] combine(double[][] data) {
        double[] temp = {0,0};
        for (int i = 0; i < data.length; i++) {
            temp[0] += data[i][0];
            temp[1] += data[i][1];
        }
        return new double[]{temp[0]/data.length, temp[1]/data.length};
    }
}
