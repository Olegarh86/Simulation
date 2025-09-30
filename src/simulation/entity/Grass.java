package simulation.entity;

import simulation.map.Coordinate;

public class Grass extends Entity{

    @Override
    public String getName() {
        return "Grass";
    }

    @Override
    public boolean getAvailableToMove() {
        return true;
    }
}
