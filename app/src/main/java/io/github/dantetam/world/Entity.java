package io.github.dantetam.world;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dante on 6/13/2016.
 */
public class Entity {

    public List<Tile> locations;
    public String name;
    public Clan clan;

    public Entity() {
        locations = new ArrayList<Tile>();
    }

    public void move(World world, Tile t) {
        int deltaX = t.row - locations.get(0).row;
        int deltaY = t.col - locations.get(0).col;
        Tile center = locations.remove(0);
        for (int i = 0; i < locations.size(); i++) {
            Tile old = locations.get(i);
            Tile new = world.getTile(old.row + deltaX, old.col + deltaY);
        }
    }

}
