package simulation.entity;

import simulation.map.Coordinate;

import java.util.Queue;
import java.util.Set;

// тратит ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature{
    public Herbivore(int speed, int hp, String target) {
        super(speed, hp, target);
    }

    @Override
    public String getName() {
        return "Herbivore";
    }

    @Override
    public boolean getAvailableToMove() {
        return true;
    }
}
