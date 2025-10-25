package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

// тратит ход:
// Переместиться (чтобы приблизиться к жертве - травоядному)
// Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
// Если значение HP жертвы опускается до 0, травоядное исчезает
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
        String name = map.biMap.get(coordinate).getName();
        return !name.equals("Rock") && !name.equals("Tree") && !name.equals("Predator") && !name.equals("Grass");
    }

    @Override
    public String getTarget() {
        return TARGET;
    }

    @Override
    public void attackTarget(MapOfWorld map, Creature creature, Coordinate startCoordinate, Coordinate newCoordinate) {
        Herbivore herbivore = (Herbivore) map.biMap.get(newCoordinate);//TODO
        Predator predator = (Predator)creature;
        herbivore.decrementHp(predator.getAttackPower());
        predator.incrementHp();
        if (herbivore.isAlive()) {
            map.newBiMapOfCreatures.put(predator, startCoordinate);
        } else {
            map.biMap.put(startCoordinate, new EmptyCell());
            map.biMap.put(newCoordinate, predator);
            herbivore.decrementCountOfCreature();
            map.newBiMapOfCreatures.remove(herbivore);
            map.newBiMapOfCreatures.put(predator, newCoordinate);
        }
    }

    @Override
    protected void decrementCountOfCreature() {
        predatorsCount--;
    }

    public int getAttackPower() {
        return this.attackPower;
    }
}
