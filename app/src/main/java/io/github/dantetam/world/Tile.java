package io.github.dantetam.world;

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
        WHEAT (0),
        FISH (1),
        IRON (2);
        public int type;
        Resource(int t) {type = t;}
        static Resource fromInt(int t) {
            switch (t) {
                case 0: return WHEAT;
                case 1: return FISH;
                case 2: return IRON;
                default:
                    throw new IllegalArgumentException("Invalid terrain type");
            }
        }
    }

    public Tile(int r, int c) {
        row = r; col = c;
        resources = new ArrayList<Resource>();
    }

    public float dist(Tile t) {
        return (float)Math.sqrt(Math.pow(row - t.row, 2) + Math.pow(col - t.col, 2));
    }

}
