package io.github.dantetam.world;

import io.github.dantetam.data.QuadTree;
import io.github.dantetam.data.WorldTree;

/**
 * Created by Dante on 6/13/2016.
 */
public class World {

    //private QuadTree<Tile, int[]> tiles;
    //private WorldTree tree;
    protected Tile[][] tiles;
    public int rows, cols;

    public World(int totalRows, int totalCols) {
        //tree = new WorldTree();
        tiles = new Tile[totalRows][totalCols];
        rows = totalRows; cols = totalCols;
    }

    public void init(int[][] biomes, int[][] terrain, int[][] resources) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile tile = new Tile(r, c);
                tile.biome = Tile.Biome.fromInt(biomes[r][c]);

            }
        }
    }

    public Tile getTile(int r, int c) {
        if (r < 0 || c < 0 || r >= tiles.length || c >= tiles[0].length) {
            throw new IllegalArgumentException("Out of bounds or degenerate grid");
        }
        return tiles[r][c];
    }

}
