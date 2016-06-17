package io.github.dantetam.world;

import io.github.dantetam.data.TwoWayComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dante on 6/13/2016.
 */
public class Tile {

    public int row, col;
    public int elevation;
    public Biome biome; //combined climate of land
    public Terrain terrain; //shape of the land
    public List<Resource> resources;

    public enum Biome {
        SEA (0),
        ICE (1),
        TUNDRA (2),
        DESERT (3),
        STEPPE (4),
        FOREST (5),
        RAINFOREST (6);
        public int type;
        Biome(int t) {type = t;}
        static Biome fromInt(int t) {
            switch (t) {
                case 0: return SEA;
                case 1: return ICE;
                case 2: return TUNDRA;
                case 3: return DESERT;
                case 4: return STEPPE;
                case 5: return FOREST;
                case 6: return RAINFOREST;
                default:
                    throw new IllegalArgumentException("Invalid biome type");
            }
        }
    }

    public enum Terrain {
        PLAINS (0),
        HILLS (1),
        CLIFFS (2),
        MOUNTAINS (3),
        SHALLOW_SEA (4),
        DEEP_SEA (5);
        public int type;
        Terrain(int t) {type = t;}
        static Terrain fromInt(int t) {
            switch (t) {
                case 0: return PLAINS;
                case 1: return HILLS;
                case 2: return CLIFFS;
                case 3: return MOUNTAINS;
                case 4: return SHALLOW_SEA;
                case 5: return DEEP_SEA;
                default:
                    throw new IllegalArgumentException("Invalid terrain type");
            }
        }
    }

    public enum Resource {
        WHEAT,
        FISH,
        IRON;
        private static Resource[] types = {WHEAT, FISH, IRON};
        static Resource fromInt(int t) {
            if (t >= 0 && t < types.length) {
                return types[t];
            }
            throw new IllegalArgumentException("Invalid terrain type");
        }
    }

    public Tile(int r, int c) {
        row = r; col = c;
        resources = new ArrayList<Resource>();
    }

    public float dist(Tile t) {
        return (float)Math.sqrt(Math.pow(row - t.row, 2) + Math.pow(col - t.col, 2));
    }

    public int compare(Tile a, Tile b) { //Default behavior
        int dy = compareY(a, b);
        if (dy != 0) {
            return dy;
        }
        return compareX(a, b);
    }

    public boolean equals(Object a) {
        if (!(a instanceof Tile)) {
            return false;
        }
        Tile t = (Tile) a;
        return row == t.row && col == t.col;
    }

    public int compareX(Tile a, Tile b) {return a.row - b.row;}
    public int compareY(Tile a, Tile b) {return a.col - b.col;}

}
