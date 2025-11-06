package simulation;

import simulation.actions.*;
import simulation.config.Config;
import simulation.config.configFactories.ConfigFactory;
import simulation.config.configFactories.FactoryConfigFactories;
import simulation.view.input.ConsoleInput;
import simulation.view.output.ConsoleOutput;
import simulation.view.input.Input;
import simulation.view.output.Output;
import simulation.world.WorldMap;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput(input);
        FactoryConfigFactories factoryConfigFactory = new FactoryConfigFactories();
        ConfigFactory configFactory = factoryConfigFactory.getConfigFactory(input, output);
        Config config = configFactory.get();
        WorldMap world = new WorldMap(config.gameMapHeight, config.gameMapWidth);
        List<Action> initActions = List.of(new InitAction(), new OutputAction(output));
        List<Action> turnActions = List.of(new TurnAction(), new ReSpawnAction(), new OutputAction(output));

        Simulation simulation = new Simulation(input, config, world, initActions, turnActions);
        try {
            simulation.startSimulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
