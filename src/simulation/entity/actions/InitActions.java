package simulation.entity.actions;

import simulation.entity.*;
import simulation.entity.creatures.Herbivore;
import simulation.entity.creatures.Predator;
import simulation.entity.factories.*;
import simulation.world.MapOfWorld;
import simulation.utils.config.Config;

public class InitActions implements Actions {
    private final MapOfWorld world;
    private final Config config;
    private final RockFactory rockFactory;
    private final TreeFactory treeFactory;
    private final GrassFactory grassFactory;
    private final HerbivoreFactory herbivoreFactory;
    private final PredatorFactory predatorFactory;
//    List<Class<? extends Entity>> classes;

    public InitActions(MapOfWorld world, Config config) {
        rockFactory = new RockFactory();
        treeFactory = new TreeFactory();
        grassFactory = new GrassFactory();
        herbivoreFactory = new HerbivoreFactory();
        predatorFactory = new PredatorFactory();
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
            MapOfWorld.setEntitiesToRandomCoordinate(world, config, rockFactory);
        }
        if (Tree.getTreesCount() < 1) {
            MapOfWorld.setEntitiesToRandomCoordinate(world, config, treeFactory);
        }
        if (Herbivore.getHerbivoresCount() < 1) {
            MapOfWorld.setEntitiesToRandomCoordinate(world, config, herbivoreFactory);
        }
        if (Predator.getPredatorsCount() < 1) {
            MapOfWorld.setEntitiesToRandomCoordinate(world, config, predatorFactory);
        }
        if (Grass.getGrassCount() < 1) {
            MapOfWorld.setEntitiesToRandomCoordinate(world, config, grassFactory);
        }
    }
}
