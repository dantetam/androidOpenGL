package io.github.dantetam.world;

import java.util.List;

import io.github.dantetam.nemesisthirdtest.Solid;

/**
 * Created by Dante on 6/17/2016.
 * Not sure about the usage of this class since classes within "world",
 * should never be exposed to OpenGL intrinsics.
 */
public abstract class Representable {

    protected List<Solid> model;

}
