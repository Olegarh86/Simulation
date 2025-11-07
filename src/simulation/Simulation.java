package simulation;

import simulation.actions.Action;
import simulation.view.input.Input;
import simulation.world.WorldMap;
import simulation.config.Config;

import java.util.List;

public class Simulation {
    public static int countOfMoves;
    private final WorldMap world;
    private final Config config;
    private final Input input;
    private final List<Action> initActions;
    private final List<Action> turnActions;

    public Simulation(Input input, Config config, WorldMap world, List<Action> initActions, List<Action> turnActions) {
        this.input = input;
        this.config = config;
        this.world = world;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation() {
        ThreadKeyListener threadKeyListener = new ThreadKeyListener(config.delayBetweenMovesInMilliseconds, input);
        threadKeyListener.start();

        for (Action action : initActions) {
            action.execute(world, config);
        }
        while (!threadKeyListener.stopSimulation) {
            try {
                Thread.sleep(config.delayBetweenMovesInMilliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (threadKeyListener.lock) {
                while (threadKeyListener.pauseSimulation) {
                    try {
                        threadKeyListener.lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            for (Action action : turnActions) {
                action.execute(world, config);
            }
        }
    }

    static class ThreadKeyListener extends Thread {
        protected final Object lock = new Object();
        private static final String resume = "1";
        private static final String oneTurn = "";
        private static final String stop = " ";
        private final int delayBetweenMovesInMilliseconds;
        private final Input input;
        volatile boolean pauseSimulation = false;
        volatile boolean stopSimulation = false;

        public ThreadKeyListener(int delayBetweenMovesInMilliseconds, Input input) {
            this.delayBetweenMovesInMilliseconds = delayBetweenMovesInMilliseconds;
            this.input = input;
        }

        @Override
        public void run() {
            while (!stopSimulation) {
                String answerFromUser = input.read();

                switch (answerFromUser) {
                    case resume -> resumeEndlessSimulation();
                    case oneTurn -> oneTurn();
                    case stop -> stopSimulation();
                    default -> pauseSimulation();
                }
            }
        }

        private void oneTurn() {
            resumeEndlessSimulation();
            try {
                Thread.sleep(delayBetweenMovesInMilliseconds / 2);
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


