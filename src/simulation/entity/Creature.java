package simulation.entity;


import com.google.common.collect.BiMap;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.Iterator;
import java.util.Map;

public abstract class Creature extends Entity implements Actions {
    private final int speed;
    private int hp;
    private final String target;


    public String getTarget() {
        return target;
    }

    public int getHp() {
        return hp;
    }

    public void decrementHp() {
        this.hp--;
    }

    public void incrementHp() {
        this.hp++;
    }

    public int getSpeed() {
        return speed;
    }

    public Creature(int speed, int hp, String target) {
        this.speed = speed;
        this.hp = hp;
        this.target = target;
    }

    public void makeMove(MapOfWorld map, BiMap<Entity, Coordinate> newBiMapOfCreatures, Creature creature,
                                  Coordinate oldCoordinate) {

    }
}
