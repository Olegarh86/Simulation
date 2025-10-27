package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;
import simulation.world.PathFinder;

import java.util.List;
import java.util.Map;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;

    public Creature(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public abstract String getTarget();

    public abstract List<String> GetObstacles();

    protected abstract void attackTarget(MapOfWorld world, Creature creature, Coordinate startCoordinate, Coordinate newCoordinate, Map<Entity, Coordinate> newCreaturesCoordinates);

    protected abstract int getAttackPower();

    protected int getHp() {
        return hp;
    }

    protected void decrementHp(int hp) {
         this.hp -= hp ;
    }

    public void decrementHp() {
        this.hp--;
    }

    protected void incrementHp() {
        this.hp++;
    }

    protected void incrementHp(int hp) {
        this.hp += hp;
    }

    protected int getSpeed() {
        return speed;
    }

    public void makeMove(MapOfWorld world, Creature currentCreature, Coordinate startCoordinate, PathFinder pathFinder, Map<Entity, Coordinate> newCreaturesCoordinates) {
        List<Coordinate> wayToTarget = pathFinder.findWayToTarget(world, currentCreature, startCoordinate);

        if (wayToTarget.isEmpty()) {
            currentCreature.decrementHp();

            if (currentCreature.isAlive()) {
                creatureRemainsAlive(currentCreature, startCoordinate, newCreaturesCoordinates);
            } else {
                world.deleteEntity(startCoordinate);
            }
            return;
        }
        Coordinate newCoordinate = selectNewCoordinateWithCreatureSpeed(currentCreature, wayToTarget);

        if (world.getEntity(newCoordinate).getName().equals(currentCreature.getTarget())) {
            currentCreature.attackTarget(world, currentCreature, startCoordinate, newCoordinate, newCreaturesCoordinates);
        } else {
            world.moveCreatureToEmptyCell(currentCreature, startCoordinate, newCoordinate);
            currentCreature.decrementHp();

            if (currentCreature.isAlive()) {
                creatureRemainsAlive(currentCreature, newCoordinate, newCreaturesCoordinates);
            } else {
                world.deleteEntity(newCoordinate);
            }
        }
    }

    public Coordinate selectNewCoordinateWithCreatureSpeed(Creature currentCreature, List<Coordinate> wayToTarget) {

        if (wayToTarget.size() > currentCreature.getSpeed()) {
            return wayToTarget.get(currentCreature.getSpeed());
        } else {
            return wayToTarget.get(wayToTarget.size() - 1);
        }
    }

    public boolean isAlive() {
        return this.getHp() > 0;
    }

    public void creatureRemainsAlive(Creature currentCreature, Coordinate coordinate, Map<Entity, Coordinate> newCreaturesCoordinates) {
        newCreaturesCoordinates.put(currentCreature, coordinate);
    }
}
