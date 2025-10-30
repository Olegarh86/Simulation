package simulation;

import simulation.entity.actions.Actions;
import simulation.entity.actions.InitActions;
import simulation.entity.actions.TurnActions;
import simulation.utils.config.Config;
import simulation.utils.config.ConfigFactory;
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
        ConfigFactory configFactory = Config.chooseConfigFactory(input, output);
        Config config = configFactory.getConfig();
        MapOfWorld world = new MapOfWorld(config);
        List<Actions> actions = List.of(new InitActions(world, config), new TurnActions(world, config));

        Simulation simulation = new Simulation(output, input, config, world, actions);
        try {
            simulation.startSimulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
