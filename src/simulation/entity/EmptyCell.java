package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class EmptyCell extends Entity {
    private static final String NAME = "EmptyCell";

    protected EmptyCell() {}

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return true;
    }
}
