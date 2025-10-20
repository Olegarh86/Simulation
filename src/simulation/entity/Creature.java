package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.map.BFSPathFinder;
import simulation.map.PathFinder;
import simulation.utils.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public abstract class Creature extends Entity implements Actions {
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

    public void makeMove(Config config, MapOfWorld map, Map.Entry<Entity, Coordinate> entry) {
        CreatorOfEmptyCells emptyCellCreator =  new CreatorOfEmptyCells();
        PathFinder bfsPathFinder = new BFSPathFinder(config);

        Coordinate startCoordinate = entry.getValue();
        Entity currentEntity = entry.getKey();
        Creature currentCreature = (Creature) currentEntity;

        ArrayList<Coordinate> wayToTarget = bfsPathFinder.findWayToTarget(map, currentCreature, startCoordinate);
        Collections.reverse(wayToTarget);

        if (wayToTarget.isEmpty()) {
            currentCreature.decrementHp();

            if (currentCreature.getHp() > 0) {
                map.newBiMapOfCreatures.put(currentCreature, startCoordinate);
            } else {
                map.biMap.put(startCoordinate, emptyCellCreator.createEntity(config));
            }
            return;
        }

        Coordinate newCoordinate;

        if (wayToTarget.size() > currentCreature.getSpeed()) {
            newCoordinate = wayToTarget.get(currentCreature.getSpeed());
        } else {
            newCoordinate = wayToTarget.get(wayToTarget.size() - 1);
        }

        if (map.biMap.get(newCoordinate).getName().equals(currentCreature.getTarget())) {
            currentCreature.attackTarget(map, currentCreature, startCoordinate, newCoordinate);
        } else {
            map.biMap.put(startCoordinate, emptyCellCreator.createEntity(config));
            currentCreature.decrementHp();

            if (currentCreature.getHp() > 0) {
                map.newBiMapOfCreatures.forcePut(currentCreature, newCoordinate);
                map.biMap.put(newCoordinate, currentCreature);
            } else {
                map.biMap.put(newCoordinate, emptyCellCreator.createEntity(config));
            }
        }
    }
}
