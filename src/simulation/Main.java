package simulation;

import simulation.actions.*;
import simulation.config.Config;
import simulation.config.ConfigFactory;
import simulation.config.FactoryConfigFactories;
import simulation.io.ConsoleInput;
import simulation.io.ConsoleOutput;
import simulation.io.Input;
import simulation.io.Output;
import simulation.world.MapOfWorld;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ConsoleInput();
        FactoryConfigFactories factoryConfigFactory = new FactoryConfigFactories();
        ConfigFactory configFactory = factoryConfigFactory.getConfigFactory(input, output);
        Config config = configFactory.getConfig();
        MapOfWorld world = new MapOfWorld(config);
        List<Actions> initActions = List.of(new InitActions(), new OutputActions());
        List<Actions> turnActions = List.of(new TurnActions(), new ReSpawnActions(), new OutputActions());

        Simulation simulation = new Simulation(input, config, world, initActions, turnActions);
        try {
            simulation.startSimulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
