package simulation;

import simulation.entity.actions.Actions;
import simulation.utils.io.Input;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

import java.util.List;

public class Simulation {
    public static int countOfMoves;
    private final MapOfWorld world;
    private final Config config;
//    private final Output output;
    private final Input input;
    private final List<Actions> initActions;
    private final List<Actions> turnActions;

    public Simulation(Input input, Config config, MapOfWorld world, List<Actions> initActions, List<Actions> turnActions) {
//        this.output = output;
        this.input = input;
        this.config = config;
        this.world = world;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation() throws InterruptedException {
        ThreadKeyListener threadKeyListener = new ThreadKeyListener(config, input);
        threadKeyListener.start();
//        Renderer renderer = new BaseSimulationRenderer(output);
        for(Actions action : initActions) {
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

            for (Actions action : turnActions) {
                action.execute(world, config);
            }
//            output.printCount(countOfMoves++);
//            output.printMessageControls();
//            renderer.draw(config, world);
        }
    }

    static class ThreadKeyListener extends Thread {
        private static final String resume = "1";
        private static final String oneTurn = "";
        private static final String stop = " ";
        volatile boolean pauseSimulation = false;
        volatile boolean stopSimulation = false;
        final Object lock = new Object();
        private final Config config;
        private final Input input;

        public ThreadKeyListener(Config config, Input input) {
            this.config = config;
            this.input = input;
        }

        @Override
        public void run() {
            while (!stopSimulation) {
                String answerFromUser = input.readString();

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


