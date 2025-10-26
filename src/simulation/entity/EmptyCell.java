package simulation.entity;

import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

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
    public boolean cellAvailableToMove(MapOfWorld world, Coordinate coordinate) {
        return true;
    }

    @Override
    public void decrementCountOfEntity() {
        emptyCellsCount--;
    }
}
