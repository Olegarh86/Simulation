package simulation.actions;

import simulation.Simulation;
import simulation.config.Config;
import simulation.view.output.ConsoleOutput;
import simulation.view.output.Output;
import simulation.view.renderer.BaseSimulationRenderer;
import simulation.view.renderer.Renderer;
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
