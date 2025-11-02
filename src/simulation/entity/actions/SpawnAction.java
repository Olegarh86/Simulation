package simulation.entity.actions;

import simulation.entity.Entity;
import simulation.utils.config.Config;
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
            Coordinate randomCoordinate = Coordinate.chooseEmptyRandomCoordinate(world, config);
            world.setEntity(randomCoordinate, entity);
        }
    }
}
