package simulation.entity;

import simulation.map.Coordinate;

public class Rock extends Entity{

    @Override
    public String getName() {
        return "Rock";
    }

    public boolean getAvailableToMove() {
        return false;
    }
}
