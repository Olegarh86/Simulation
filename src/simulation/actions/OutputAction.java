package simulation.actions;

import simulation.Simulation;
import simulation.config.Config;
import simulation.entity.Entity;
import simulation.view.output.Output;
import simulation.view.renderer.BaseSimulationRenderer;
import simulation.view.renderer.Renderer;
import simulation.world.Coordinate;
import simulation.world.WorldMap;

import java.util.HashMap;
import java.util.Map;

public class OutputAction implements Action {
    private final Output output;
    private final Renderer renderer;
    Map<Integer, Map<Coordinate, Entity>> countMoveStateWorld = new HashMap<>();

    public OutputAction(Output output) {
        this.output = output;
        this.renderer = new BaseSimulationRenderer(output);
    }

    @Override
    public void execute(WorldMap world, Config config) {
        output.count(Simulation.countOfMoves++);
        renderer.draw(world, config);
        output.controls();
        save(Simulation.countOfMoves, world);
    }

    private void save(Integer count, WorldMap world) {
        countMoveStateWorld.put(count, world.getCoordinatesEntities());
    }
}
