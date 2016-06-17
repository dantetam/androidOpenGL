package io.github.dantetam.nemesisthirdtest;

import io.github.dantetam.world.*;

/**
 * Created by Dante on 6/17/2016.
 * A connection between the classes of the world package that store world data,
 * and the OpenGL classes that render the world.
 */
public class WorldHandler {

    public World world;

    public WorldHandler() {
        world = new World(33, 33);
    }

}
