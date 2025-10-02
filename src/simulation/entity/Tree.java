package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Tree extends Entity{

    @Override
    public String getName() {
        return "Tree";
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return false;
    }
}
