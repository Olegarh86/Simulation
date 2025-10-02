package simulation.entity;

import com.google.common.collect.BiMap;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.utils.PathFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

// тратит ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature implements Eateble {
    private static final String TARGET = "Grass";

    public Herbivore(int speed, int hp, String target) {
        super(speed, hp, target);
    }

    @Override
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
            newBiMapOfCreatures.put(creature, newCoordinate);
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

    @Override
    public String getName() {
        return "Herbivore";
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        String name = map.biMap.get(coordinate).getName();
        return !name.equals("Rock") && !name.equals("Tree") && !name.equals("Predator") && !name.equals("Herbivore");
    }
}
