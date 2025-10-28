package simulation.entity.creatures;

import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.List;

public class Predator extends Creature {
    public static final String NAME = "Predator";
    private static final String TARGET = "Herbivore";
    private static int predatorsCount = 0;
    private static final List<String> obstacles = List.of("Rock", "Tree", "Grass", "Predator");
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

    public static int getCount() {
        return predatorsCount;
    }

    @Override
    public String getName() {
        return NAME;
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
    protected void attackTarget(MapOfWorld world, Creature predator, Coordinate startCoordinate, Coordinate newCoordinate) {
        Creature target = (Creature) world.getEntity(newCoordinate);
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
