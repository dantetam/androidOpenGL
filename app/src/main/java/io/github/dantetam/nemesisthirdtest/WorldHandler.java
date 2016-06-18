package io.github.dantetam.nemesisthirdtest;

import java.util.ArrayList;
import java.util.List;

import io.github.dantetam.world.*;

/**
 * Created by Dante on 6/17/2016.
 * A connection between the classes of the world package that store world data,
 * and the OpenGL classes that render the world.
 */
public class WorldHandler {

    public World world;
    public WorldGenerator worldGenerator;
    private List<Solid> tilesStored = null;

    public WorldHandler() {
        world = new World(33, 33);
        worldGenerator = new WorldGenerator(world);
        worldGenerator.init();
    }

    public List<Solid> worldRep() {
        return tileRep();
    }

    private List<Solid> tileRep() {
        if (tilesStored == null) {
            tilesStored = new ArrayList<Solid>();
            for (int r = 0; r < world.rows; r++) {
                for (int c = 0; c < world.cols; c++) {
                    Tile tile = world.getTile(r, c);
                    Solid solid = new Solid();
                    solid.move(r, 0, c);
                    solid.scale(1, 0.5f * tile.terrain.type, 1);
                    //solid.rotate(0, 0, 0, 0);
                    solid.color(Tile.Biome.colorFromInt(tile.biome.type));
                    tilesStored.add(solid);
                }
            }
        }
        return tilesStored;
    }

    public List<Object> concat(List<Object> a, List<Object> b) {
        List<Object> combined = new ArrayList<Object>();
        for (Object o: a) combined.add(o);
        for (Object o: b) combined.add(o);
        return combined;
    }

}
