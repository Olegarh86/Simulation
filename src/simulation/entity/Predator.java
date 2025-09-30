package simulation.entity;

import simulation.map.Coordinate;

import java.util.Queue;
import java.util.Set;

// тратит ход:
// Переместиться (чтобы приблизиться к жертве - травоядному)
// Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
// Если значение HP жертвы опускается до 0, травоядное исчезает
public class Predator extends Creature{
    private final int attackPower;

    public Predator(int speed, int hp, String target, int attackPower) {
        super(speed, hp, target);
        this.attackPower = attackPower;
    }

    @Override
    public String getName() {
        return "Predator";
    }

    @Override
    public boolean getAvailableToMove() {
        return false;
    }
}
