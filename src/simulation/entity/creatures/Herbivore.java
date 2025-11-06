package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.entity.Rock;
import simulation.entity.Tree;
import simulation.world.Coordinate;
import simulation.world.WorldMap;

import java.util.List;

public class Herbivore extends Creature{
    private static final Class<? extends Entity> TARGET = Grass.class;
    private static final List<Class<? extends Entity>> OBSTACLES = List.of(Rock.class, Tree.class, Herbivore.class, Predator.class);

    public Herbivore(int speed, int hp) {
        super(speed, hp);
    }

    @Override
    protected Class<? extends Entity> getTarget() {
        return TARGET;
    }

    @Override
    protected List<Class<? extends Entity>> getObstacles() {
        return OBSTACLES;
    }

    @Override
    protected void attackTarget(WorldMap world, Coordinate startCoordinate, Coordinate newCoordinate) {
        moveCreature(world, startCoordinate, newCoordinate);
        this.incrementHp();
    }
}
