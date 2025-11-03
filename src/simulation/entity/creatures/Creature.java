package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.config.Config;
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

    protected abstract Class<? extends Entity> getTarget();

    protected abstract List<Class<? extends Entity>> getObstacles();

    protected abstract void attackTarget(MapOfWorld world, Creature creature, Coordinate startCoordinate, Coordinate newCoordinate);

    protected abstract int getAttackPower();

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

    public void makeMove(MapOfWorld world, Config config, Creature currentCreature, Coordinate startCoordinate) {
        PathFinder pathFinder = new BFSPathFinder(config);
        List<Coordinate> wayToTarget = pathFinder.findWayToTarget(world, currentCreature.getTarget(), currentCreature.getObstacles(), startCoordinate);
        Coordinate newCoordinate = selectCoordinateForMoveWithCreatureSpeed(currentCreature, wayToTarget, startCoordinate);

        if (newCoordinate.equals(startCoordinate)) {
            currentCreature.decrementHp();

            if (currentCreature.isDied()) {
                world.deleteEntity(startCoordinate);
            }
            return;
        }

        if (world.getEntity(newCoordinate).isPresent() && currentCreature.getTarget().equals(world.getEntity(newCoordinate).get().getClass())) {
            currentCreature.attackTarget(world, currentCreature, startCoordinate, newCoordinate);
        } else {
            world.moveCreature(currentCreature, startCoordinate, newCoordinate);
            currentCreature.decrementHp();

            if (currentCreature.isDied()) {
                world.deleteEntity(newCoordinate);
            }
        }
    }

    private Coordinate selectCoordinateForMoveWithCreatureSpeed(Creature currentCreature, List<Coordinate> wayToTarget, Coordinate startCoordinate) {
        if (wayToTarget.isEmpty()) {
            return startCoordinate;
        }
        if (wayToTarget.size() > currentCreature.getSpeed()) {
            return wayToTarget.get(currentCreature.getSpeed());
        }
        return wayToTarget.getLast();
    }

    protected boolean isDied() {
        return this.getHp() < 1;
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
