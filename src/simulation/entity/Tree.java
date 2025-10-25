package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Tree extends Entity{
    static int treesCount = 0;
    private static final String NAME = "Tree";

    protected Tree() {
        treesCount++;
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
        treesCount--;
    }
}
