package simulation.entity.actions;

import simulation.entity.*;
import simulation.entity.creatures.Herbivore;
import simulation.entity.creatures.Predator;
import simulation.entity.factories.*;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

import java.util.Set;

public class InitActions implements Actions {
    private final MapOfWorld world;
    private final Config config;
//    List<Class<? extends Entity>> classes;

    public InitActions(MapOfWorld world, Config config) {
        this.world = world;
        this.config = config;
//        classes = List.of(Rock.class, Tree.class, Grass.class, Herbivore.class, Predator.class);
    }

    @Override
    public void execute() {
//        for (Class<? extends Entity> currentClass : classes) {
//            try {
//                Method method = currentClass.getDeclaredMethod("getCount");
//                method.
//                int count = (int) method.invoke(null);
//                if (count < 1) {
//                    setEntitiesToRandomCoordinate(world, config, creatorOfRocks);
//                }
//            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
        if (Rock.getRocksCount() < 1) {
            setEntitiesToRandomCoordinate(world, config, new RockFactory());
        }
        if (Tree.getTreesCount() < 1) {
            setEntitiesToRandomCoordinate(world, config, new TreeFactory());
        }
        if (Herbivore.getHerbivoresCount() < 1) {
            setEntitiesToRandomCoordinate(world, config, new HerbivoreFactory());
        }
        if (Predator.getPredatorsCount() < 1) {
            setEntitiesToRandomCoordinate(world, config, new PredatorFactory());
        }
        if (Grass.getGrassCount() < 1) {
            setEntitiesToRandomCoordinate(world, config, new GrassFactory());
        }
    }

    public static void setEntitiesToRandomCoordinate(MapOfWorld world, Config config, EntityFactory entityFactory) {
        Set<Entity> entities = entityFactory.createMultipleEntities(world, config);
        Coordinate randomCoordinate;
        for (Entity entity : entities) {
            randomCoordinate = Coordinate.chooseEmptyRandomCoordinate(world, config);
            world.setEntity(randomCoordinate, entity);
            EmptyCell.emptyCellsCount--;
        }
    }
}
