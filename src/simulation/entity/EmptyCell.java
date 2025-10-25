package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class EmptyCell extends Entity {
    public static int emptyCellsCount = 0;
    private static final String NAME = "EmptyCell";

    public EmptyCell() {
        emptyCellsCount++;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return true;
    }

    @Override
    protected void decrementCountOfEntity() {
        emptyCellsCount--;
    }
}
