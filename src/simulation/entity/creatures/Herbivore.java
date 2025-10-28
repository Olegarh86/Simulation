package simulation.entity.creatures;

import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.List;

public class Herbivore extends Creature{
    public static final String NAME = "Herbivore";
    private static final String TARGET = "Grass";
    private static final List<String> obstacles = List.of("Rock", "Tree", "Herbivore", "Predator");
    private static final boolean movable = true;
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
    public List<String> GetObstacles() {
        return obstacles;
    }

    @Override
    protected void attackTarget(MapOfWorld world, Creature herbivore, Coordinate startCoordinate, Coordinate newCoordinate) {
        world.deleteEntity(newCoordinate);
        world.moveCreatureToEmptyCell(herbivore, startCoordinate, newCoordinate);
        herbivore.incrementHp();
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
    public boolean isMovable() {
        return movable;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
