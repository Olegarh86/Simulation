package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class EmptyCell extends Entity{

    @Override
    public String getName() {
        return "EmptyCell";
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        return true;
    }
}
