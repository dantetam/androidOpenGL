package io.github.dantetam.nemesisthirdtest;

import io.github.dantetam.world.DiamondSquare;
import io.github.dantetam.world.Tile;
import io.github.dantetam.world.World;

/**
 * Created by Dante on 6/17/2016.
 */
public class WorldGenerator {

    private World world;

    public WorldGenerator(World w) {
        world = w;
    }

    public void init() {
        int[][] biomes = new DiamondSquare(world.rows, 6, 0.4, Tile.Biome.numBiomes).seed(870).getIntTerrain();
        int[][] terrains = new DiamondSquare(world.rows, 6, 0.4, 6, Tile.Terrain.numTerrains).seed(0417).getIntTerrain();
        Tile.Resource[][] resources = makeNewResources(world.rows, world.cols);
        world.init(biomes, terrains, resources);
    }

    private Tile.Resource[][] makeNewResources(int rows, int cols) {
        Tile.Resource[][] temp = new Tile.Resource[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile.Resource index;
                if (Math.random() < 0.1) {
                    index = Tile.Resource.randomResource();
                }
                else {
                    index = Tile.Resource.NO_RESOURCE;
                }
                temp[r][c] = index;
            }
        }
        return temp;
    }

}
