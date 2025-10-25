package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Rock extends Entity {
    static int rocksCount = 0;
    private static final String NAME = "Rock";

    protected Rock() {
        rocksCount++;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return false;
    }

    @Override
    protected void decrementCountOfEntity() {
        rocksCount--;
    }
}
