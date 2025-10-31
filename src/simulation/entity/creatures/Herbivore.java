package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.entity.Rock;
import simulation.entity.Tree;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.List;

public class Herbivore extends Creature{
    public static final String NAME = "Herbivore";
    Class<? extends Entity>  TARGET = Grass.class;
    private static final List<Class<? extends Entity>> obstacles = List.of(Rock.class, Tree.class, Herbivore.class, Predator.class);
    private static final boolean movable = true;
    private static int herbivoresCount;

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
    public Class<? extends Entity> getTarget() {
        return TARGET;
    }

    @Override
    public List<Class<? extends Entity>> GetObstacles() {
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
