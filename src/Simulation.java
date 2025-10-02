import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import simulation.entity.*;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.*;

import simulation.utils.Config;
import simulation.utils.PathFinder;
import simulation.utils.SimulationRenderer;

public class Simulation {
    static int countOfMoves = 0;

    public static void main(String[] args) {
        MapOfWorld map = new MapOfWorld();
        initActions(map);
        SimulationRenderer.draw(map);

        while (!map.biMapOfCreatures.isEmpty()) {

            BiMap<Entity, Coordinate> newBiMapOfCreatures = HashBiMap.create();

            for (Map.Entry<Entity, Coordinate> entry : map.biMapOfCreatures.entrySet()) {

                Coordinate oldCoordinate = entry.getValue();
                Entity entity = entry.getKey();
                Creature creature = (Creature) entity;
                creature.makeMove(map, newBiMapOfCreatures, creature, oldCoordinate);

                countOfMoves++;
            }

            map.biMapOfCreatures = newBiMapOfCreatures;

            for (Map.Entry<Coordinate, Entity> entry : newBiMapOfCreatures.inverse().entrySet()) {
                map.biMap.forcePut(entry.getKey(), entry.getValue());
            }

            SimulationRenderer.draw(map);
            System.out.println(countOfMoves);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
