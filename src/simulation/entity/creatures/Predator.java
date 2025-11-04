package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.entity.Rock;
import simulation.entity.Tree;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.List;

public class Predator extends Creature {
    private static final Class<? extends Entity> TARGET = Herbivore.class;
    private static final List<Class<? extends Entity>> OBSTACLES = List.of(Rock.class, Tree.class, Grass.class, Predator.class);
    private final int attackPower;

    public Predator(int speed, int hp, int attackPower) {
        super(speed, hp);
        this.attackPower = attackPower;
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
    protected void attackTarget(MapOfWorld world, Coordinate startCoordinate, Coordinate newCoordinate) {
        Creature target = (Creature) world.getEntity(newCoordinate).orElseThrow();
        this.incrementHp(Math.min(target.getHp(), this.getAttackPower()));
        target.decrementHp(this.getAttackPower());

        if (target.isDied()) {
            world.moveCreature(this, startCoordinate, newCoordinate);
        }
    }

    protected int getAttackPower() {
        return this.attackPower;
    }
}
