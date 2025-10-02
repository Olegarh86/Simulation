package simulation.entity;


import com.google.common.collect.BiMap;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.utils.PathFinder;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Creature extends Entity implements Actions {
    private final int speed;
    private int hp;
    private static String TARGET;


    public abstract String getTarget();

    public int getHp() {
        return hp;
    }

    public void decrementHp() {
        this.hp--;
    }

    public void incrementHp() {
        this.hp++;
    }

    public int getSpeed() {
        return speed;
    }

    public Creature(int speed, int hp, String target) {
        this.speed = speed;
        this.hp = hp;
        TARGET = target;
    }

    public void makeMove(MapOfWorld map, BiMap<Entity, Coordinate> newBiMapOfCreatures, Creature creature,
                                  Coordinate oldCoordinate) {
        ArrayList<Coordinate> wayToTarget = PathFinder.findWayToTarget(map, creature, oldCoordinate);
        Collections.reverse(wayToTarget);

        if (wayToTarget.isEmpty()) {

            creature.decrementHp();

            if (creature.getHp() > 0) {
                newBiMapOfCreatures.put(creature, oldCoordinate);
            } else {
                map.biMap.put(oldCoordinate, new EmptyCell());
            }
            return;
        }

        Coordinate newCoordinate;

        if (wayToTarget.size() > creature.getSpeed()) {
            newCoordinate = wayToTarget.get(creature.getSpeed());
        } else {
            newCoordinate = wayToTarget.get(wayToTarget.size() - 1);
        }

        if (map.biMap.get(newCoordinate).getName().equals(TARGET)) {
            map.biMap.put(oldCoordinate, new EmptyCell());
            map.biMap.put(newCoordinate, creature);
            creature.incrementHp();
            newBiMapOfCreatures.forcePut(creature, newCoordinate);
        } else {

            map.biMap.put(oldCoordinate, new EmptyCell());
            creature.decrementHp();

            if (creature.getHp() > 0) {
                newBiMapOfCreatures.put(creature, newCoordinate);
                map.biMap.put(newCoordinate, creature);
            } else {
                map.biMap.put(newCoordinate, new EmptyCell());
            }
        }
    }
}
