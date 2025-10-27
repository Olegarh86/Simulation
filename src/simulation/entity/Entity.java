package simulation.entity;

import simulation.entity.creatures.Herbivore;
import simulation.entity.creatures.Predator;

public abstract class Entity implements Comparable<Entity> {

    public abstract String getName();

    public abstract void decrementCountOfEntity();

    public abstract boolean isMovable();

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

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

