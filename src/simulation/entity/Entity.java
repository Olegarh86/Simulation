package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public abstract class Entity {

    abstract public String getName();

    public abstract boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate);

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

