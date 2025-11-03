package simulation.entity.factories;

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
    public EntityFactory entityFactory;


    public FactoryEntityFactories(Class<? extends Entity> classEntity, Config config) {
        this.entityFactory = getEntityFactory(classEntity.getName());
        this.amount = getAmount(classEntity.getName(), config);
    }

    private EntityFactory getEntityFactory(String classEntity) {
        switch (classEntity) {
            case ROCK -> {
                return new RockFactory();
            }
            case TREE -> {
                return new TreeFactory();
            }
            case GRASS -> {
                return new GrassFactory();
            }
            case HERBIVORE -> {
                return new HerbivoreFactory();
            }
            case PREDATOR -> {
                return new PredatorFactory();
            }
            default -> throw new RuntimeException(ERROR);
        }
    }

    private int getAmount(String classEntity, Config config) {
        switch (classEntity) {
            case ROCK -> {
                return config.numberOfRocks;
            }
            case TREE -> {
                return config.numberOfTrees;
            }
            case GRASS -> {
                return config.numberOfGrasses;
            }
            case HERBIVORE -> {
                return config.numberOfHerbivores;
            }
            case PREDATOR -> {
                return config.numberOfPredators;
            }
            default -> throw new RuntimeException(ERROR);
        }
    }
}
