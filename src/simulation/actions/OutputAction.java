package simulation.actions;

import simulation.Simulation;
import simulation.config.Config;
import simulation.view.output.Output;
import simulation.view.renderer.BaseSimulationRenderer;
import simulation.view.renderer.Renderer;
import simulation.world.WorldMap;

public class OutputAction implements Action {
    Output output;
    Renderer renderer;

    public OutputAction(Output output) {
        this.output = output;
        this.renderer = new BaseSimulationRenderer(output);
    }

    @Override
    public void execute(WorldMap world, Config config) {
        output.printCount(Simulation.countOfMoves++);
        renderer.draw(world, config);
        output.printMessageControls();
    }
}
