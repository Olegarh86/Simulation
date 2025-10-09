import simulation.entity.*;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.*;

import simulation.utils.Config;
import simulation.utils.SimulationRenderer;

public class Simulation {
    static int countOfMoves = 0;
    private static volatile boolean paused = false;
    private MapOfWorld map;
    public final Object lock = new Object();

    public Simulation(MapOfWorld map) {
        this.map = map;
    }

    public static void main(String[] args) {
        MapOfWorld map = new MapOfWorld();
        try {
            new Simulation(map).startEndlessSimulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        initActions(map);
        SimulationRenderer.draw(map);

    }

    private void startEndlessSimulation() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    synchronized (lock) {
                        while (paused) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }

                    nextTurn(map);
                }
            }
        });

        thread1.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String answerFromUser = scanner.nextLine();
            if (answerFromUser.equalsIgnoreCase("p")) {
                pause();
            } else if (answerFromUser.equalsIgnoreCase("r")) {
                resume();
            }
        }

    }

    private void pause() {
        paused = true;
    }

    private void resume() {
        synchronized (lock) {
            paused = false;
            lock.notifyAll();
        }

    }

    // действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
    public static void initActions(MapOfWorld map) {
        setupRocks(map);
        setupTrees(map);
        setupGrasses(map);
        setupHerbivores(map);
        setupPredators(map);
    }

    private static void setupRocks(MapOfWorld map) {
        int count = 0;
        while (count < Config.getNumberOfRocks()) {
            Entity entity = new Rock();
            setEntityToRandomCoordinate(map, entity);
            count++;
        }
    }

    private static void setupTrees(MapOfWorld map) {
        int count = 0;
        while (count < Config.getNumberOfTrees()) {
            Entity entity = new Tree();
            setEntityToRandomCoordinate(map, entity);
            count++;
        }
    }

    private static void setupGrasses(MapOfWorld map) {
        int count = 0;
        while (count < Config.getNumberOfGrasses()) {
            Entity entity = new Grass();
            setEntityToRandomCoordinate(map, entity);
            count++;
        }
    }

    private static void setupPredators(MapOfWorld map) {
        int count = 0;
        while (count < Config.getNumberOfPredators()) {
            Creature creature = new Predator(Config.getPredatorsSpeed(), Config.getPredatorsHp(), Config.getTargetForPredators(), Config.getPredatorsAttackPower());
            setEntityToRandomCoordinate(map, creature);
            count++;
        }
    }

    private static void setupHerbivores(MapOfWorld map) {
        int count = 0;
        while (count < Config.getNumberOfHerbivores()) {
            Creature creature = new Herbivore(Config.getHerbivoresSpeed(), Config.getHerbivoresHp(), Config.getTargetForHerbivores());
            setEntityToRandomCoordinate(map, creature);
            count++;
        }
    }

    public static void setEntityToRandomCoordinate(MapOfWorld map, Entity entity) {
        Coordinate randomCoordinate = Coordinate.getRandomCoordinate();

        while (!(map.biMap.get(randomCoordinate).getClass().getSimpleName().equals("EmptyCell"))) {
            randomCoordinate = Coordinate.getRandomCoordinate();
        }

        if (entity.getName().equals("Herbivore") || entity.getName().equals("Predator")) {
            Creature creature = (Creature) entity;
            map.biMap.put(randomCoordinate, entity);
            map.biMapOfCreatures.put(creature, randomCoordinate);
        } else {
            map.biMap.put(randomCoordinate, entity);
        }
    }

    // приостановить бесконечный цикл симуляции и рендеринга
    public static void pauseSimulation(Simulation simulation) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        synchronized (simulation) {
//            scanner.nextLine();
//            map.wait();
//            scanner.nextLine();
            System.out.println("Im dont sleep");
            simulation.notify();
        }
    }

    // просимулировать и отрендерить один ход
    public static void nextTurn(MapOfWorld map) {

        for (Map.Entry<Entity, Coordinate> entry : map.biMapOfCreatures.entrySet()) {

            Creature creature = (Creature) entry.getKey();
            creature.makeMove(map, entry);
            countOfMoves++;
        }

        map.biMapOfCreatures.clear();
        for (Map.Entry<Coordinate, Entity> entry : map.newBiMapOfCreatures.inverse().entrySet()) {

            map.biMap.forcePut(entry.getKey(), entry.getValue());
            map.biMapOfCreatures.put(entry.getValue(), entry.getKey());
        }

        int grassesCount = 0;
        int herbivoresCount = 0;
        int predatorsCount = 0;

        for (Entity entity : map.biMap.values()) {
            if (entity.getName().equals("Herbivore")) {
                herbivoresCount++;
            } else if (entity.getName().equals("Predator")) {
                predatorsCount++;
            } else if (entity.getName().equals("Grass")) {
                grassesCount++;
            }
        }

        if (herbivoresCount < 1) {
            setupHerbivores(map);
        }

        if (predatorsCount < 1) {
            setupPredators(map);
        }

        if (grassesCount < 1) {
            setupGrasses(map);
        }

        map.newBiMapOfCreatures.clear();
        countOfMoves++;

        try {
            Thread.sleep(Config.DELAY_BETWEEN_MOVES_IN_MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        SimulationRenderer.draw(map);
    }

    //  действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или травоядных, если их осталось слишком мало
    public static void turnActions(MapOfWorld map) {

    }
}
