package simulation.actions;

import simulation.Simulation;
import simulation.config.Config;
import simulation.io.ConsoleOutput;
import simulation.io.Output;
import simulation.io.renderer.BaseSimulationRenderer;
import simulation.io.renderer.Renderer;
import simulation.world.MapOfWorld;

public class OutputActions implements Actions{
    Output output = new ConsoleOutput();
    Renderer renderer = new BaseSimulationRenderer(output);

    @Override
    public void execute(MapOfWorld world, Config config) {
        output.printCount(Simulation.countOfMoves++);
        renderer.draw(config, world);
        output.printMessageControls();
    }
}
