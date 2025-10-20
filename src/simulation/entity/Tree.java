package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Tree extends Entity{
    private static final String NAME = "Tree";

    protected Tree() {}

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return false;
    }
}
