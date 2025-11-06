package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.world.pathFinder.BFSPathFinder;
import simulation.world.Coordinate;
import simulation.world.WorldMap;
import simulation.world.pathFinder.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;

    public Creature(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public void makeMove(WorldMap world, Coordinate startCoordinate) {
        PathFinder pathFinder = new BFSPathFinder();
        List<Coordinate> wayToTarget = pathFinder.find(world, this.getTarget(), this.getObstacles(), startCoordinate);
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
            moveCreature(world, startCoordinate, newCoordinate);
            this.decrementHp();

            if (this.isDied()) {
                world.deleteEntity(newCoordinate);
            }
        }
    }

    public void moveCreature(WorldMap world,  Coordinate startCoordinate, Coordinate newCoordinate) {
        world.setEntity(newCoordinate, this);
        world.deleteEntity(startCoordinate);
    }

    protected abstract Class<? extends Entity> getTarget();

    protected abstract List<Class<? extends Entity>> getObstacles();

    protected abstract void attackTarget(WorldMap world, Coordinate startCoordinate, Coordinate newCoordinate);

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
}
