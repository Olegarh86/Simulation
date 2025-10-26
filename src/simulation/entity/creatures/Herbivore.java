package simulation.entity.creatures;

import simulation.entity.EmptyCell;
import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

public class Herbivore extends Creature{
    private static final String NAME = "Herbivore";
    private static final String TARGET = "Grass";
    private static int herbivoresCount = 0;

    public Herbivore(int speed, int hp) {
        super(speed, hp);
        herbivoresCount++;
    }

    public static int getHerbivoresCount() {
        return herbivoresCount;
    }

    public static int getCount() {
        return herbivoresCount;
    }

    @Override
    public String getTarget() {
        return TARGET;
    }

    @Override
    protected void attackTarget(MapOfWorld world, Creature herbivore, Coordinate startCoordinate, Coordinate newCoordinate) {
        Entity target = world.coordinatesEntities.get(newCoordinate);
        world.coordinatesEntities.put(startCoordinate, new EmptyCell());
        target.decrementCountOfEntity();
        world.coordinatesEntities.put(newCoordinate, herbivore);
        herbivore.incrementHp();
        world.newCreaturesCoordinates.put(herbivore, newCoordinate);
    }

    @Override
    protected int getAttackPower() {
        return 0;
    }

    @Override
    public void decrementCountOfEntity() {
        herbivoresCount--;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld world, Coordinate coordinate) {
        String name = world.coordinatesEntities.get(coordinate).getName();
        return !name.equals("Rock") && !name.equals("Tree") && !name.equals("Predator") && !name.equals("Herbivore");
    }
}
