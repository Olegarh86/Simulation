package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public abstract class Entity implements Comparable<Entity> {
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

    protected abstract void decrementCountOfEntity();

    @Override
    public int compareTo(Entity o) {
        if (this.getName().equals("Herbivore") && o.getName().equals("Predator")) {
            return -1;
        }
        if (this.getName().equals("Predator") && o.getName().equals("Herbivore")) {
            return 1;
        }

        return Integer.compare(this.hashCode(), o.hashCode());
    }

    public void setEntity(MapOfWorld map, Coordinate coordinate) {
        map.coordinatesEntities.put(coordinate, this);
    }
}

