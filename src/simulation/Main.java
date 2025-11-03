package simulation;

import simulation.entity.actions.*;
import simulation.utils.config.Config;
import simulation.utils.config.ConfigFactory;
import simulation.utils.config.FactoryConfigFactories;
import simulation.utils.io.ConsoleInput;
import simulation.utils.io.ConsoleOutput;
import simulation.utils.io.Input;
import simulation.utils.io.Output;
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
