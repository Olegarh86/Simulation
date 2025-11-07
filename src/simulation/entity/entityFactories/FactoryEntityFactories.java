package simulation.entity.entityFactories;

import simulation.entity.Entity;
import simulation.config.Config;

public class FactoryEntityFactories {
    private final static String ROCK = "simulation.entity.Rock";
    private final static String TREE = "simulation.entity.Tree";
    private final static String GRASS = "simulation.entity.Grass";
    private final static String HERBIVORE = "simulation.entity.creatures.Herbivore";
    private final static String PREDATOR = "simulation.entity.creatures.Predator";
    private final static String ERROR = "Factory controller can't create factory for that class";
    public final int amount;
    public final EntityFactory entityFactory;


    public FactoryEntityFactories(Class<? extends Entity> classEntity, Config config) {
        this.entityFactory = getEntityFactory(classEntity.getName());
        this.amount = getAmount(classEntity.getName(), config);
    }

    private EntityFactory getEntityFactory(String classEntity) {
        return switch (classEntity) {
            case ROCK -> new RockFactory();
            case TREE -> new TreeFactory();
            case GRASS -> new GrassFactory();
            case HERBIVORE -> new HerbivoreFactory();
            case PREDATOR -> new PredatorFactory();
            default -> throw new RuntimeException(ERROR);
        };
    }

    private int getAmount(String classEntity, Config config) {
        return switch (classEntity) {
            case ROCK -> config.numberOfRocks;
            case TREE -> config.numberOfTrees;
            case GRASS -> config.numberOfGrasses;
            case HERBIVORE -> config.numberOfHerbivores;
            case PREDATOR -> config.numberOfPredators;
            default -> throw new RuntimeException(ERROR);
        };
    }
}
