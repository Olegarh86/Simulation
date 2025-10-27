package simulation.entity.creatures;

import simulation.entity.EmptyCell;
import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;
import simulation.world.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;
    private static final List<String> obstacles = List.of();

    public Creature(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public abstract String getTarget();

    public abstract List<String> GetObstacles();

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

    protected int getSpeed() {
        return speed;
    }

    @Override
    public void setEntity(MapOfWorld world, Coordinate coordinate) {
        world.coordinatesEntities.put(coordinate, this);
        world.creaturesCoordinates.put(this, coordinate);
    }

    public void makeMove(MapOfWorld world, Creature currentCreature, Coordinate startCoordinate, PathFinder pathFinder) {
        List<Coordinate> wayToTarget = pathFinder.findWayToTarget(world, currentCreature, startCoordinate);

        if (wayToTarget.isEmpty()) {
            currentCreature.decrementHp();

            if (currentCreature.isAlive()) {
                creatureRemainsAlive(world, currentCreature, startCoordinate);
            } else {
                creatureDies(world, currentCreature, startCoordinate);
            }
            return;
        }

        Coordinate newCoordinate = selectNewCoordinateWithCreatureSpeed(currentCreature, wayToTarget);

        if (world.coordinatesEntities.get(newCoordinate).getName().equals(currentCreature.getTarget())) {
            currentCreature.attackTarget(world, currentCreature, startCoordinate, newCoordinate);
        } else {
            Entity emptyCell = world.coordinatesEntities.replace(newCoordinate, currentCreature);
            world.coordinatesEntities.put(startCoordinate, emptyCell);
            currentCreature.decrementHp();

            if (currentCreature.isAlive()) {
                creatureRemainsAlive(world, currentCreature, newCoordinate);
            } else {
                creatureDies(world, currentCreature, newCoordinate);
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

    public void creatureRemainsAlive(MapOfWorld world, Creature currentCreature, Coordinate coordinate) {
        world.newCreaturesCoordinates.put(currentCreature, coordinate);
    }

    public void creatureDies(MapOfWorld world, Creature currentCreature, Coordinate coordinate) {
        world.coordinatesEntities.put(coordinate, new EmptyCell());
        currentCreature.decrementCountOfEntity();
    }
}
