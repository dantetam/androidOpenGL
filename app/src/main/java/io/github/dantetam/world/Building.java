package io.github.dantetam.world;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dante on 6/16/2016.
 */
public class Building extends Entity {

    public List<Item> items;

    public Building() {
        items = new ArrayList<Item>();
    }

}
