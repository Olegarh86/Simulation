package simulation.entity.creatures;

import simulation.entity.Entity;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;
import simulation.world.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;

    public Creature(int speed, int hp) {
        this.speed = speed;
        this.hp = hp;
    }

    public abstract String getTarget();

    public abstract List<String> GetObstacles();

    protected abstract void attackTarget(MapOfWorld world, Creature creature, Coordinate startCoordinate, Coordinate newCoordinate);

    protected abstract int getAttackPower();

    protected int getHp() {
        return hp;
    }

    protected void decrementHp(int hp) {
         this.hp -= hp ;
    }

    public void decrementHp() {
        this.hp--;
    }

    protected void incrementHp() {
        this.hp++;
    }

    protected void incrementHp(int hp) {
        this.hp += hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void makeMove(MapOfWorld world, Creature currentCreature, Coordinate startCoordinate, PathFinder pathFinder) {
        Coordinate newCoordinate = pathFinder.findCellForMove(world, currentCreature, startCoordinate);

        if (newCoordinate.equals(startCoordinate)) {
            currentCreature.decrementHp();

            if (currentCreature.isDied()) {
                world.deleteEntity(startCoordinate);
            }
            return;
        }

        if (currentCreature.getTarget().equals(world.getEntity(newCoordinate).getName())) {
            currentCreature.attackTarget(world, currentCreature, startCoordinate, newCoordinate);
        } else {
            world.moveCreatureToEmptyCell(currentCreature, startCoordinate, newCoordinate);
            currentCreature.decrementHp();

            if (currentCreature.isDied()) {
                world.deleteEntity(newCoordinate);
            }
        }
    }

    public boolean isDied() {
        return this.getHp() < 1;
    }
}
