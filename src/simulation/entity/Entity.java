package simulation.entity;

import simulation.entity.creatures.Herbivore;
import simulation.entity.creatures.Predator;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

public abstract class Entity implements Comparable<Entity> {
    protected static final String NAME = "";

    public String getName() {
        return NAME;
    }

    public void setEntity(MapOfWorld world, Coordinate coordinate) {
        world.coordinatesEntities.put(coordinate, this);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public abstract void decrementCountOfEntity();

    @Override
    public int compareTo(Entity o) {
        if (this.getName().equals(Herbivore.NAME) && o.getName().equals(Predator.NAME)) {
            return -1;
        }
        if (this.getName().equals(Predator.NAME) && o.getName().equals(Herbivore.NAME)) {
            return 1;
        }

        return Integer.compare(this.hashCode(), o.hashCode());
    }
}

