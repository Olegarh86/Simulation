package simulation.entity;

import com.google.common.collect.BiMap;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.map.PathFinder;
import simulation.utils.config.Config;

import java.util.Map;

public class TurnActions implements Actions{
    Map<Entity, Coordinate> biMapOfCreatures;
    MapOfWorld map;
    PathFinder pathFinder;

    public TurnActions(MapOfWorld map, PathFinder pathFinder) {
        this.map = map;
        this.biMapOfCreatures = map.biMapOfCreatures;
        this.pathFinder = pathFinder;
    }

    @Override
    public void execute() {
        for (Map.Entry<Entity, Coordinate> entry : map.biMapOfCreatures.entrySet()) {

            Creature creature = (Creature) entry.getKey();
            creature.makeMove(map, entry, pathFinder);
        }
        map.biMapOfCreatures.clear();

        for (Map.Entry<Entity, Coordinate> entry : map.newBiMapOfCreatures.entrySet()) {

            map.biMap.forcePut(entry.getValue(), entry.getKey());
            map.biMapOfCreatures.put(entry.getKey(), entry.getValue());
        }
        map.newBiMapOfCreatures.clear();
    }
}
