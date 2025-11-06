package simulation.actions;

import simulation.entity.Entity;
import simulation.config.Config;
import simulation.world.Coordinate;
import simulation.world.WorldMap;

import java.util.Random;
import java.util.function.Supplier;

public class SpawnAction implements Action {
    private final Supplier<Entity> entitySupplier;
    private final int amount;

    public SpawnAction(Supplier<Entity> entitySupplier, int amount) {
        this.entitySupplier = entitySupplier;
        this.amount = amount;
    }

    @Override
    public void execute(WorldMap world, Config config) {
        for (int i = 0; i < amount; i++) {
            Entity entity = entitySupplier.get();
            Coordinate randomCoordinate = chooseEmptyRandomCoordinate(world, config);
            world.setEntity(randomCoordinate, entity);
        }
    }

    public static Coordinate getRandomCoordinate(Config config) {
        Random random = new Random();
        int randomRow = random.nextInt(config.gameMapHeight);
        int randomColumn = random.nextInt(config.gameMapWidth);
        return new Coordinate(randomRow, randomColumn);
    }

    public Coordinate chooseEmptyRandomCoordinate(WorldMap world, Config config) {
        Coordinate randomCoordinate = getRandomCoordinate(config);

        while (world.getEntity(randomCoordinate).isPresent()) {
            randomCoordinate = getRandomCoordinate(config);
        }
        return randomCoordinate;
    }
}
