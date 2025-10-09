package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Grass extends Entity {

    @Override
    public String getName() {
        return "Grass";
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return true;
    }
}
