package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.map.PathFinder;

import java.util.Map;

public class TurnActions implements Actions{
    Map<Entity, Coordinate> biMapOfCreatures;
    MapOfWorld map;
    PathFinder pathFinder;

    public TurnActions(MapOfWorld map, PathFinder pathFinder) {
        this.map = map;
        this.biMapOfCreatures = map.creaturesCoordinates;
        this.pathFinder = pathFinder;
    }

    @Override
    public void execute() {
        for (Map.Entry<Entity, Coordinate> entry : map.creaturesCoordinates.entrySet()) {

            Creature creature = (Creature) entry.getKey();
            creature.makeMove(map, entry, pathFinder);
        }
        map.creaturesCoordinates.clear();

        for (Map.Entry<Entity, Coordinate> entry : map.newCreaturesCoordinates.entrySet()) {

            map.coordinatesEntities.put(entry.getValue(), entry.getKey());
            map.creaturesCoordinates.put(entry.getKey(), entry.getValue());
        }
        map.newCreaturesCoordinates.clear();
    }
}
