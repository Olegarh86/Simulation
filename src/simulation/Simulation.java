package simulation;
import simulation.entity.*;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.*;

import simulation.utils.Config;
import simulation.utils.PathFinder;
import simulation.utils.SimulationRenderer;

public class Simulation {
    public static final Random RANDOM = new Random();
    static int countOfMoves = 0;

    public static void main(String[] args) {
        MapOfWorld map = new MapOfWorld();
        initActions(map);
        SimulationRenderer.draw(map);
        for (Map.Entry<Coordinate, Entity> entrySet : map.map.entrySet()) {

            if (map.setOfCreatures.contains(entrySet.getValue())) {
                Creature currentCreature = ((Creature) entrySet.getValue());
                ArrayList<Coordinate> wayToTarget = PathFinder.findWayToTarget(map, entrySet.getKey(), currentCreature.getTarget());
                Collections.reverse(wayToTarget);
                if (wayToTarget.isEmpty()) {
                    continue;
                }
                Coordinate coordinateForMove;
                if (wayToTarget.size() > currentCreature.getSpeed()) {
                    coordinateForMove = wayToTarget.get(currentCreature.getSpeed());
                } else {
                    coordinateForMove = wayToTarget.get(wayToTarget.size() - 1);
                }
                map.map.put(coordinateForMove, currentCreature);
                map.map.put(entrySet.getKey(), new EmptyCell());
                countOfMoves++;
            }
        }
        SimulationRenderer.draw(map);
        System.out.println(countOfMoves);
    }

    // просимулировать и отрендерить один ход
    public void nextTurn() {

    }

    // запустить бесконечный цикл симуляции и рендеринга
    public void startSimulation() {

    }

    // приостановить бесконечный цикл симуляции и рендеринга
    public void pauseSimulation() {

    }

    // действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
    public static void initActions(MapOfWorld map) {
        map.setupStartPosition(Config.getNumberOfRocks(), Config.getNumberOfTrees(), Config.getNumberOfGrasses(), Config.getNumberOfHerbivores(), Config.getNumberOfPredators());

    }

    //  действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или травоядных, если их осталось слишком мало
    public void turnActions() {

    }

}
