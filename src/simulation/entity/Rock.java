package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Rock extends Entity {
    private static final String NAME = "Rock";

    protected Rock() {}

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return false;
    }
}
