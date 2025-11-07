package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.WorldMap;


public class Predator extends Creature {
    private static final Class<? extends Entity> TARGET = Herbivore.class;
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
    protected void attackTarget(WorldMap world, Coordinate startCoordinate, Coordinate newCoordinate) {
        Creature target = (Creature) world.getEntity(newCoordinate).orElseThrow();
        this.incrementHp(Math.min(target.getHp(), this.getAttackPower()));
        target.decrementHp(this.getAttackPower());

        if (target.isDied()) {
            moveCreature(world, startCoordinate, newCoordinate);
        }
    }

    protected int getAttackPower() {
        return this.attackPower;
    }
}
