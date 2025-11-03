package simulation.actions;

import simulation.entity.Entity;
import simulation.config.Config;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.function.Supplier;

public class SpawnAction implements Actions{
    private final Supplier<Entity> entitySupplier;
    private final int amount;

    public SpawnAction(Supplier<Entity> entitySupplier, int amount) {
        this.entitySupplier = entitySupplier;
        this.amount = amount;
    }

    @Override
    public void execute(MapOfWorld world, Config config) {
        for (int i = 0; i < amount; i++) {
            Entity entity = entitySupplier.get();
            Coordinate randomCoordinate = chooseEmptyRandomCoordinate(world, config);
            world.setEntity(randomCoordinate, entity);
        }
    }

    public Coordinate chooseEmptyRandomCoordinate(MapOfWorld world, Config config) {
        Coordinate randomCoordinate = Coordinate.getRandomCoordinate(config);

        while (world.getEntity(randomCoordinate).isPresent()) {
            randomCoordinate = Coordinate.getRandomCoordinate(config);
        }
        return randomCoordinate;
    }
}
