package io.github.dantetam.world;

/**
 * Created by Dante on 6/13/2016.
 */
public class Tile {

    public int row, col;
    public int elevation;

    public enum Biome {
        SEA,
        ICE,
        TUNDRA,
        DESERT,
        STEPPE,
        FOREST,
        RAINFOREST
    }

    public enum Terrain {
        PLAINS,
        HILLS,
        CLIFFS,
        MOUNTAINS,
        SHALLOW_SEA,
        DEEP_SEA
    }

    public enum Resource {
        WHEAT,
        FISH,
        IRON
    }

    public Tile(int r, int c) {
        row = r; col = c;
    }

    public float dist(Tile t) {
        return (float)Math.sqrt(Math.pow(row - t.row, 2) + Math.pow(col - t.col, 2));
    }

}
