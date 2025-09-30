package simulation.entity;

import simulation.map.Coordinate;

public class EmptyCell extends Entity{

    @Override
    public String getName() {
        return "EmptyCell";
    }

    @Override
    public boolean getAvailableToMove() {
        return true;
    }
}
