import simulation.entity.actions.Actions;
import simulation.entity.actions.InitActions;
import simulation.entity.actions.TurnActions;
import simulation.utils.io.Input;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;
import simulation.utils.config.ConfigFactory;
import simulation.utils.io.ConsoleInput;
import simulation.utils.io.ConsoleOutput;
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

    private Simulation() {
        this.output = new ConsoleOutput();
        this.input = new ConsoleInput();
        ConfigFactory configFactory = Config.chooseConfigFactory(input, output);
        this.config = configFactory.getConfig();
        this.world = new MapOfWorld(config);
        this.actions = List.of(new InitActions(world, config), new TurnActions(world, config));
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
        ThreadKeyListener threadKeyListener = new ThreadKeyListener(config, input, output);
        threadKeyListener.start();
        Renderer renderer = new BaseSimulationRenderer();
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


