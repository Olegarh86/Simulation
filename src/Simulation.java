import simulation.entity.*;
import simulation.entity.Creator;
import simulation.entity.CreatorOfGrasses;
import simulation.entity.CreatorOfHerbivores;
import simulation.entity.CreatorOfPredators;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.utils.*;

import java.util.List;
import java.util.Map;

public class Simulation {
    private static int countOfMoves = 0;
    private final MapOfWorld map;
    private final Renderer renderer;
    private final Config config;
    private final ThreadKeyListener threadKeyListener;

    private Simulation() {
        ConsoleScanner consoleScanner = new ConsoleScanner();
        ConfigFactory configFactory = Config.changeConfigFactory(consoleScanner);
        config = configFactory.get();
        this.map = new MapOfWorld(config);
        threadKeyListener = new ThreadKeyListener(config);
        threadKeyListener.start();
        initActions();
        renderer = new BaseSimulationRenderer();
        renderer.draw(config, map);
    }

    public static void main(String[] args) {

        Simulation simulation = new Simulation();
        try {
            simulation.startEndlessSimulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void startEndlessSimulation() throws InterruptedException {

        do {
            synchronized (threadKeyListener.lock) {
                while (threadKeyListener.pauseSimulation) {
                    try {
                        threadKeyListener.lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            nextTurn();
            renderer.draw(config, map);
            try {
                Thread.sleep(config.delayBetweenMovesInMilliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!threadKeyListener.stopSimulation);
    }


    // действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
    private void initActions() {
        List<Creator> creators = List.of(new CreatorOfRocks(), new CreatorOfTrees(), new CreatorOfGrasses(),
                new CreatorOfHerbivores(), new CreatorOfPredators());

        for (Creator creator : creators) {
            setEntitiesToRandomCoordinate(config , map, creator);
        }
    }

    private static void setEntitiesToRandomCoordinate(Config config, MapOfWorld map, Creator creator) {
        Coordinate randomCoordinate = Coordinate.getRandomCoordinate(config);

        for (Entity entity : creator.createMultipleEntities(config)) {

            while (!(map.biMap.get(randomCoordinate).getClass().getSimpleName().equals("EmptyCell"))) {
                randomCoordinate = Coordinate.getRandomCoordinate(config);
            }

            if (entity.getName().equals("Herbivore") || entity.getName().equals("Predator")) {
                map.biMap.put(randomCoordinate, entity);
                map.biMapOfCreatures.put(entity, randomCoordinate);
            } else {
                map.biMap.put(randomCoordinate, entity);
            }
        }
    }

    // просимулировать и отрендерить один ход
    private void nextTurn() {

        for (Map.Entry<Entity, Coordinate> entry : map.biMapOfCreatures.entrySet()) {

            Creature creature = (Creature) entry.getKey();
            creature.makeMove(config, map, entry);
            countOfMoves++;
        }

        map.biMapOfCreatures.clear();
        for (Map.Entry<Coordinate, Entity> entry : map.newBiMapOfCreatures.inverse().entrySet()) {

            map.biMap.forcePut(entry.getKey(), entry.getValue());
            map.biMapOfCreatures.put(entry.getValue(), entry.getKey());
        }

        addEntitiesIfTheyRunOut();

        map.newBiMapOfCreatures.clear();
        countOfMoves++;
        System.out.println(countOfMoves);
    }

    //  действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или травоядных, если их осталось слишком мало
    public static void turnActions(MapOfWorld map) {

    }

    private void addEntitiesIfTheyRunOut() {
        int grassesCount = 0;
        int herbivoresCount = 0;
        int predatorsCount = 0;

        for (Entity entity : map.biMap.values()) {
            switch (entity.getName()) {
                case "Herbivore" -> herbivoresCount++;
                case "Predator" -> predatorsCount++;
                case "Grass" -> grassesCount++;
            }
        }

        if (herbivoresCount < 1) {
            setEntitiesToRandomCoordinate(config, map, new CreatorOfHerbivores());
        }

        if (predatorsCount < 1) {
            setEntitiesToRandomCoordinate(config, map, new CreatorOfPredators());
        }

        if (grassesCount < 1) {
            setEntitiesToRandomCoordinate(config, map, new CreatorOfGrasses());
        }
    }

    static class ThreadKeyListener extends Thread {
        volatile boolean pauseSimulation = false;
        volatile boolean stopSimulation = false;
        final Object lock = new Object();
        Config config;

        public ThreadKeyListener(Config config) {
            this.config = config;
        }

        @Override
        public void run() {
            ConsoleScanner consoleScanner = new ConsoleScanner();

            while (!stopSimulation) {
                String answerFromUser = consoleScanner.readString();

                switch (answerFromUser) {
                    case "1" -> pauseSimulation();
                    case "2" -> resumeEndlessSimulation();
                    case "" -> oneTurn();
                    case " " -> stopSimulation();
                    default -> { pauseSimulation();
                        System.out.println("""
                        Incorrect key. Please, enter key:
                        1 - to pause the simulation
                        2 - to start endless simulation
                        Enter - to the next turn
                        Space + Enter - to stop the simulation""");
                    }
                }
            }
        }

        private void oneTurn() {

            resumeEndlessSimulation();
            try {
                Thread.sleep(config.delayBetweenMovesInMilliseconds / 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pauseSimulation();
        }

        private void pauseSimulation() {
            pauseSimulation = true;
        }

        private void resumeEndlessSimulation() {
            synchronized (lock) {
                pauseSimulation = false;
                lock.notifyAll();
            }
        }

        private void stopSimulation() {
            synchronized (lock) {
                pauseSimulation = false;
                stopSimulation = true;
                lock.notifyAll();
            }
        }
    }
}


