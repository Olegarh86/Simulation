package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

// тратит ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature{
    private final String TARGET = "Grass";

    public Herbivore(int speed, int hp, String target) {
        super(speed, hp, target);
    }

    @Override
    public String getTarget() {
        return this.TARGET;
    }

    @Override
    public void attackTarget(MapOfWorld map, Creature creature, Coordinate oldCoordinate, Coordinate newCoordinate) {
        map.biMap.put(oldCoordinate, new EmptyCell());
        map.biMap.put(newCoordinate, creature);
        creature.incrementHp();
        map.newBiMapOfCreatures.forcePut(creature, newCoordinate);
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
