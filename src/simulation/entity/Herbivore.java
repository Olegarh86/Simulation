package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

// тратит ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature{
    private static final String NAME = "Herbivore";
    private static final String TARGET = "Grass";
    static int herbivoresCount = 0;

    protected Herbivore(int speed, int hp) {
        super(speed, hp);
        herbivoresCount++;
    }

    @Override
    public String getTarget() {
        return TARGET;
    }

    @Override
    public void attackTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate, Coordinate newCoordinate) {
        Grass grass = (Grass) map.biMap.get(newCoordinate);
        map.biMap.put(startCoordinate, new EmptyCell());
        grass.decrementCountOfCreature();
        map.biMap.put(newCoordinate, creature);
        creature.incrementHp();
        map.newBiMapOfCreatures.put(creature, newCoordinate);
    }

    @Override
    protected void decrementCountOfCreature() {
        herbivoresCount--;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        String name = map.biMap.get(coordinate).getName();
        return !name.equals("Rock") && !name.equals("Tree") && !name.equals("Predator") && !name.equals("Herbivore");
    }
}
