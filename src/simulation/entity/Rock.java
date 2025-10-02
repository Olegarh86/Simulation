package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Rock extends Entity{

    @Override
    public String getName() {
        return "Rock";
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return false;
    }
}
