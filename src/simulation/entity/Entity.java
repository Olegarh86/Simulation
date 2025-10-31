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
        if (this.getClass().equals(Herbivore.class) && o.getClass().equals(Predator.class)) {
            return -1;
        }
        if (this.getClass().equals(Predator.class) && o.getClass().equals(Herbivore.class)) {
            return 1;
        }

        return Integer.compare(this.hashCode(), o.hashCode());
    }
}

