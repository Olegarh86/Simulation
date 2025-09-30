package simulation.entity;


import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.*;

public abstract class Creature extends Entity implements Actions {
    private final int speed;
    private final int hp;
    private final String target;


    public String getTarget() {
        return target;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public Creature(int speed, int hp, String target) {
        this.speed = speed;
        this.hp = hp;
        this.target = target;
    }

    public void makeMove() {
//        findAllCellsAvailableForMovement()
    }
}
