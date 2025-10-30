package simulation;

import simulation.entity.actions.Actions;
import simulation.utils.io.Input;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;
import simulation.utils.io.Output;
import simulation.utils.io.renderer.BaseSimulationRenderer;
import simulation.utils.io.renderer.Renderer;

import java.util.List;

public class Simulation {
    private static int countOfMoves;
    private final MapOfWorld world;
    private final Config config;
    private final Output output;
    private final Input input;
    private final List<Actions> actions;

    public Simulation(Output output, Input input, Config config, MapOfWorld world, List<Actions> actions) {
        this.output = output;
        this.input = input;
        this.config = config;
        this.world = world;
        this.actions = actions;
    }

    public void startSimulation() throws InterruptedException {
        ThreadKeyListener threadKeyListener = new ThreadKeyListener(config, input, output);
        threadKeyListener.start();
        Renderer renderer = new BaseSimulationRenderer(output);
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

            for (Actions action : actions) {
                action.execute();
            }
            output.printCount(countOfMoves++);
            output.printMessageControls();
            renderer.draw(config, world);
        }
    }

    static class ThreadKeyListener extends Thread {
        volatile boolean pauseSimulation = false;
        volatile boolean stopSimulation = false;
        final Object lock = new Object();
        private final Config config;
        private final Input input;
        private final Output output;

        public ThreadKeyListener(Config config, Input input, Output output) {
            this.config = config;
            this.output = output;
            this.input = input;
        }

        @Override
        public void run() {
            while (!stopSimulation) {
                String answerFromUser = input.readString();

                switch (answerFromUser) {
                    case "1" -> pauseSimulation();
                    case "2" -> resumeEndlessSimulation();
                    case "" -> oneTurn();
                    case " " -> stopSimulation();
                    default -> {
                        pauseSimulation();
                        output.incorrectKey();
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


