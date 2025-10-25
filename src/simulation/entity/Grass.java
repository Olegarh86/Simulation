package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Grass extends Entity {
    private static final String NAME = "Grass";
    static int grassCount = 0;

    protected Grass() {
        grassCount++;
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
        grassCount--;
    }
}
