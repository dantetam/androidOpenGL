package io.github.dantetam.world;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dante on 6/13/2016.
 */
public class Entity {

    public List<Tile> location;
    public String name;
    public Clan owner;

    public Entity() {
        location = new ArrayList<Tile>();
    }

    public void move(Tile t) {
        int deltaX = t.row - location.get(0).row;
        int deltaY = t.col - location.get(0).col;
    }

}
