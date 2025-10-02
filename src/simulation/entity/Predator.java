package simulation.entity;

import com.google.common.collect.BiMap;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.utils.PathFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

// тратит ход:
// Переместиться (чтобы приблизиться к жертве - травоядному)
// Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
// Если значение HP жертвы опускается до 0, травоядное исчезает
public class Predator extends Creature{
    private static final String TARGET = "Herbivore";
    private final int attackPower;

    public Predator(int speed, int hp, String target, int attackPower) {
        super(speed, hp, target);
        this.attackPower = attackPower;
    }

    @Override
    public String getName() {
        return "Predator";
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        String name = map.biMap.get(coordinate).getName();
        return !name.equals("Rock") && !name.equals("Tree") && !name.equals("Predator") && !name.equals("Grass");
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
