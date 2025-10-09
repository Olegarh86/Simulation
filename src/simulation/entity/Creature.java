package simulation.entity;


import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.utils.PathFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public abstract class Creature extends Entity implements Actions {
    private final int speed;
    private int hp;
    private static String TARGET;


    public abstract String getTarget();

    public abstract void attackTarget(MapOfWorld map, Creature creature, Coordinate oldCoordinate, Coordinate newCoordinate);

    public int getHp() {
        return hp;
    }

    public void decrementHp(int hp) {

         this.hp -= hp ;
    }

    public void decrementHp() {
        this.hp--;
    }

    public void incrementHp(int hp) {
        this.hp += hp;
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

    public void makeMove(MapOfWorld map, Map.Entry<Entity, Coordinate> entry) {

        Coordinate oldCoordinate = entry.getValue();
        Entity entity = entry.getKey();
        Creature creature = (Creature) entity;

        ArrayList<Coordinate> wayToTarget = PathFinder.findWayToTarget(map, creature, oldCoordinate);
        Collections.reverse(wayToTarget);

        if (wayToTarget.isEmpty()) {

            creature.decrementHp();

            if (creature.getHp() > 0) {
                map.newBiMapOfCreatures.put(creature, oldCoordinate);
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

        if (map.biMap.get(newCoordinate).getName().equals(creature.getTarget())) {

            creature.attackTarget(map, creature, oldCoordinate, newCoordinate);

        } else {

            map.biMap.put(oldCoordinate, new EmptyCell());
            creature.decrementHp();

            if (creature.getHp() > 0) {
                map.newBiMapOfCreatures.forcePut(creature, newCoordinate);
                map.biMap.put(newCoordinate, creature);
            } else {
                map.biMap.put(newCoordinate, new EmptyCell());
            }
        }
    }
}
