package io.github.dantetam.world;

import io.github.dantetam.data.QuadTree;

/**
 * Created by Dante on 6/13/2016.
 */
public class World {

    private QuadTree<Tile, int[]> tiles;

    public World(int totalRows, int totalCols) {
        tiles = QuadTree();
    }

    public void init() {

    }

    public Tile getTile(int r, int c) {
        /*if (r < 0 || c < 0 || r >= tiles.length || c >= tiles[0].length) {
            throw new IllegalArgumentException("Out of bounds or degenerate grid");
        }
        return tiles[r][c];*/
    }

}
