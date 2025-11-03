package simulation.entity.actions;

import simulation.entity.Entity;
import simulation.entity.Grass;
import simulation.entity.Rock;
import simulation.entity.Tree;
import simulation.entity.creatures.Herbivore;
import simulation.entity.creatures.Predator;
import simulation.entity.factories.FactoryEntityFactories;
import simulation.utils.config.Config;
import simulation.world.MapOfWorld;

import java.util.List;

public class ReSpawnActions implements Actions {
    private final static List<Class<? extends Entity>> allClasses = List.of(Rock.class, Tree.class, Grass.class, Herbivore.class, Predator.class);

    @Override
    public void execute(MapOfWorld world, Config config) {
        for (Class<? extends Entity> classEntity : allClasses) {
            int count = 0;
            for (Entity entity : world.getCoordinatesEntities().values()) {
                if (entity.getClass().equals(classEntity)) {
                    count++;
                }
                if (count >= 1) {
                    break;
                }
            }
            if (count < 1) {
                FactoryEntityFactories factoryController = new FactoryEntityFactories(classEntity, config);
                SpawnAction spawnAction = new SpawnAction(() -> factoryController.entityFactory.create(world, config),
                        factoryController.amount);
                spawnAction.execute(world, config);
            }
        }

    }
}
