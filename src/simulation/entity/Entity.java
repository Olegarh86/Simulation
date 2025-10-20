package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public abstract class Entity {
    protected static final String NAME = "";

    public String getName() {
        return NAME;
    }

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

