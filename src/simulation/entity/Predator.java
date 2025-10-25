package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

public class Predator extends Creature {
    private static final String NAME = "Predator";
    private static final String TARGET = "Herbivore";
    private final int attackPower;
    static int predatorsCount = 0;

    protected Predator(int speed, int hp, int attackPower) {
        super(speed, hp);
        this.attackPower = attackPower;
        predatorsCount++;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld map, Coordinate coordinate) {
        String name = map.coordinatesEntities.get(coordinate).getName();
        return !name.equals("Rock") && !name.equals("Tree") && !name.equals("Predator") && !name.equals("Grass");
    }

    @Override
    public String getTarget() {
        return TARGET;
    }

    @Override
    public void attackTarget(MapOfWorld map, Creature predator, Coordinate startCoordinate, Coordinate newCoordinate) {
        Creature target = (Creature) map.coordinatesEntities.get(newCoordinate);
        predator.incrementHp(Math.min(target.getHp(), predator.getAttackPower()));
        target.decrementHp(predator.getAttackPower());

        if (target.isAlive()) {
            map.newCreaturesCoordinates.put(predator, startCoordinate);
        } else {
            map.coordinatesEntities.put(startCoordinate, new EmptyCell());
            map.coordinatesEntities.put(newCoordinate, predator);
            target.decrementCountOfEntity();
            map.newCreaturesCoordinates.remove(target);
            map.newCreaturesCoordinates.put(predator, newCoordinate);
        }
    }

    @Override
    protected void decrementCountOfEntity() {
        predatorsCount--;
    }

    public int getAttackPower() {
        return this.attackPower;
    }
}
