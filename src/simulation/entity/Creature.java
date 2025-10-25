package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.map.PathFinder;

import java.util.ArrayList;
import java.util.Map;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;
    private static String target;

    protected Creature(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public abstract String getTarget();

    protected abstract void attackTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate, Coordinate newCoordinate);

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

    protected int getSpeed() {
        return speed;
    }

    @Override
    public void setEntity(MapOfWorld map, Coordinate coordinate) {
        map.biMap.put(coordinate, this);
        map.biMapOfCreatures.put(this, coordinate);
    }

    public void makeMove(MapOfWorld map, Map.Entry<Entity, Coordinate> entry, PathFinder pathFinder) {
        Coordinate startCoordinate = entry.getValue();
        Creature currentCreature = (Creature) entry.getKey();

        ArrayList<Coordinate> wayToTarget = pathFinder.findWayToTarget(map, currentCreature, startCoordinate);

        if (wayToTarget.isEmpty()) {
            currentCreature.decrementHp();

            if (currentCreature.isAlive()) {
                creatureMakesMove(map, currentCreature, startCoordinate);
            } else {
                creatureDies(map, currentCreature, startCoordinate);
            }
            return;
        }

        Coordinate newCoordinate = selectNewCoordinateWithCreatureSpeed(currentCreature, wayToTarget);

        if (map.biMap.get(newCoordinate).getName().equals(currentCreature.getTarget())) {
            currentCreature.attackTarget(map, currentCreature, startCoordinate, newCoordinate);
        } else {
            map.biMap.put(startCoordinate, new EmptyCell());
            currentCreature.decrementHp();

            if (currentCreature.isAlive()) {
                creatureMakesMove(map, currentCreature, newCoordinate);
            } else {
                creatureDies(map, currentCreature, newCoordinate);
            }
        }
    }

    public Coordinate selectNewCoordinateWithCreatureSpeed(Creature currentCreature, ArrayList<Coordinate> wayToTarget) {

        if (wayToTarget.size() > currentCreature.getSpeed()) {
            return wayToTarget.get(currentCreature.getSpeed());
        } else {
            return wayToTarget.get(wayToTarget.size() - 1);
        }
    }

    public boolean isAlive() {
        return this.getHp() > 0;
    }

    public void creatureMakesMove(MapOfWorld map, Creature currentCreature, Coordinate coordinate) {
        map.newBiMapOfCreatures.put(currentCreature, coordinate);
        map.biMap.put(coordinate, currentCreature);
    }

    public void creatureDies(MapOfWorld map, Creature currentCreature, Coordinate coordinate) {
        map.biMap.put(coordinate, new EmptyCell());
        currentCreature.decrementCountOfCreature();
    }
}
