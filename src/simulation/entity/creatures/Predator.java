package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.entity.Rock;
import simulation.entity.Tree;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.List;

public class Predator extends Creature {
    private static final Class<? extends Entity>  TARGET = Herbivore.class;
    private static int predatorsCount;
    private static final List<Class<? extends Entity>> obstacles = List.of(Rock.class, Tree.class, Grass.class, Predator.class);
    private static final boolean movable = true;
    private final int attackPower;

    public Predator(int speed, int hp, int attackPower) {
        super(speed, hp);
        this.attackPower = attackPower;
        predatorsCount++;
    }

    public static int getPredatorsCount() {
        return predatorsCount;
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
    protected void attackTarget(MapOfWorld world, Creature predator, Coordinate startCoordinate, Coordinate newCoordinate) {
        Creature target = (Creature) world.getEntity(newCoordinate).get();
        predator.incrementHp(Math.min(target.getHp(), predator.getAttackPower()));
        target.decrementHp(predator.getAttackPower());

        if (target.isDied()) {
            world.deleteEntity(newCoordinate);
            world.moveCreatureToEmptyCell(predator, startCoordinate, newCoordinate);
        }
    }

    @Override
    public void decrementCountOfEntity() {
        predatorsCount--;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }

    protected int getAttackPower() {
        return this.attackPower;
    }
}
