import simulation.entity.*;
import simulation.map.BFSPathFinder;
import simulation.map.MapOfWorld;
import simulation.map.PathFinder;
import simulation.utils.config.Config;
import simulation.utils.config.ConfigFactory;
import simulation.utils.io.ConsoleInput;
import simulation.utils.io.ConsoleOutput;
import simulation.utils.io.Input;
import simulation.utils.io.Output;
import simulation.utils.io.renderer.BaseSimulationRenderer;
import simulation.utils.io.renderer.Renderer;

import java.util.List;

public class Simulation {
    private static int countOfMoves = 0;
    private final MapOfWorld map;
    private final Renderer renderer;
    private final Config config;
    private final ThreadKeyListener threadKeyListener;
    private final Output output;
    private final InitActions initActions;
    private final List<Actions> actions;

    private Simulation() {
        Input consoleInput = new ConsoleInput();
        output = new ConsoleOutput();
        ConfigFactory configFactory = Config.changeConfigFactory(consoleInput, output);
        config = configFactory.get();
        this.map = new MapOfWorld(config);
        threadKeyListener = new ThreadKeyListener(config);
        PathFinder pathFinder = new BFSPathFinder(config);
        this.initActions = new InitActions(map, config);
        TurnActions turnActions = new TurnActions(map, pathFinder);
        this.actions = List.of(turnActions, initActions);
        renderer = new BaseSimulationRenderer();
    }

    public static void main(String[] args) {

        Simulation simulation = new Simulation();
        try {
            simulation.startSimulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void startSimulation() throws InterruptedException {
        initActions.execute();
        renderer.draw(config, map, output);
        threadKeyListener.start();

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

            for (Actions action : actions) {
                action.execute();
            }
            output.output(countOfMoves++);
            output.messageControls();
            renderer.draw(config, map, output);

            try {
                Thread.sleep(config.delayBetweenMovesInMilliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!threadKeyListener.stopSimulation);
    }

    public static void incrementCountOfMoves() {
        countOfMoves++;
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
            ConsoleInput consoleScanner = new ConsoleInput();

            while (!stopSimulation) {
                String answerFromUser = consoleScanner.readString();

                switch (answerFromUser) {
                    case "1" -> pauseSimulation();
                    case "2" -> resumeEndlessSimulation();
                    case "" -> oneTurn();
                    case " " -> stopSimulation();
                    default -> {
                        pauseSimulation();
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


