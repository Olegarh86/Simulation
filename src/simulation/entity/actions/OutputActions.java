package simulation.entity.actions;

import simulation.Simulation;
import simulation.utils.config.Config;
import simulation.utils.io.ConsoleOutput;
import simulation.utils.io.Output;
import simulation.utils.io.renderer.BaseSimulationRenderer;
import simulation.utils.io.renderer.Renderer;
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
