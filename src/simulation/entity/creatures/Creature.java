package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.config.Config;
import simulation.entity.Grass;
import simulation.world.pathFinder.BFSPathFinder;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;
import simulation.world.pathFinder.PathFinder;

import java.util.List;

public abstract class Creature extends Entity implements Comparable<Creature> {
    private final int speed;
    private int hp;

    public Creature(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public void makeMove(MapOfWorld world, Coordinate startCoordinate) {
        PathFinder pathFinder = new BFSPathFinder();
        List<Coordinate> wayToTarget = pathFinder.findWayToTarget(world, this.getTarget(), this.getObstacles(), startCoordinate);
        Coordinate newCoordinate = selectCoordinateForMoveWithCreatureSpeed(wayToTarget, startCoordinate);

        if (newCoordinate.equals(startCoordinate)) {
            this.decrementHp();

            if (this.isDied()) {
                world.deleteEntity(startCoordinate);
            }
            return;
        }

        if (world.getEntity(newCoordinate).isPresent() && this.getTarget().equals(world.getEntity(newCoordinate).get().getClass())) {
            this.attackTarget(world, startCoordinate, newCoordinate);
        } else {
            world.moveCreature(this, startCoordinate, newCoordinate);
            this.decrementHp();

            if (this.isDied()) {
                world.deleteEntity(newCoordinate);
            }
        }
    }

    protected abstract Class<? extends Entity> getTarget();

    protected abstract List<Class<? extends Entity>> getObstacles();

    protected abstract void attackTarget(MapOfWorld world, Coordinate startCoordinate, Coordinate newCoordinate);

    protected boolean isDied() {
        return this.getHp() < 1;
    }

    protected int getHp() {
        return hp;
    }

    protected void decrementHp(int hp) {
         this.hp -= hp ;
    }

    protected void decrementHp() {
        this.hp--;
    }

    protected void incrementHp() {
        this.hp++;
    }

    protected void incrementHp(int hp) {
        this.hp += hp;
    }

    private int getSpeed() {
        return speed;
    }

    private Coordinate selectCoordinateForMoveWithCreatureSpeed(List<Coordinate> wayToTarget, Coordinate startCoordinate) {
        if (wayToTarget.isEmpty()) {
            return startCoordinate;
        }
        if (wayToTarget.size() > this.getSpeed()) {
            return wayToTarget.get(this.getSpeed());
        }
        return wayToTarget.getLast();
    }

    @Override
    public int compareTo(Creature o) {
        if (this.getClass().equals(Herbivore.class) && o.getClass().equals(Predator.class)) {
            return -1;
        }
        if (this.getClass().equals(Predator.class) && o.getClass().equals(Herbivore.class)) {
            return 1;
        }
        return Integer.compare(this.hashCode(), o.hashCode());
    }
}
