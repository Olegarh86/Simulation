package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

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
    public void attackTarget(MapOfWorld map, Creature herbivore, Coordinate startCoordinate, Coordinate newCoordinate) {
        Entity target = map.coordinatesEntities.get(newCoordinate);
        map.coordinatesEntities.put(startCoordinate, new EmptyCell());
        target.decrementCountOfEntity();
        map.coordinatesEntities.put(newCoordinate, herbivore);
        herbivore.incrementHp();
        map.newCreaturesCoordinates.put(herbivore, newCoordinate);
    }

    @Override
    protected int getAttackPower() {
        return 0;
    }

    @Override
    protected void decrementCountOfEntity() {
        herbivoresCount--;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        String name = map.coordinatesEntities.get(coordinate).getName();
        return !name.equals("Rock") && !name.equals("Tree") && !name.equals("Predator") && !name.equals("Herbivore");
    }
}
