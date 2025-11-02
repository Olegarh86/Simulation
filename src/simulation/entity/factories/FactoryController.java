package simulation.entity.factories;

import simulation.entity.Entity;
import simulation.utils.config.Config;

public class FactoryController {
    public final int amount;
    public EntityFactory entityFactory;


    public FactoryController(Class<? extends Entity> classEntity, Config config) {
        this.entityFactory = getEntityFactory(classEntity.getName());
        this.amount = getAmount(classEntity.getName(), config);
    }

    private EntityFactory getEntityFactory(String classEntity) {
        switch (classEntity) {
            case "simulation.entity.Rock" -> {
                return new RockFactory();
            }
            case "simulation.entity.Tree" -> {
                return new TreeFactory();
            }
            case "simulation.entity.Grass" -> {
                return new GrassFactory();
            }
            case "simulation.entity.creatures.Herbivore" -> {
                return new HerbivoreFactory();
            }
            case "simulation.entity.creatures.Predator" -> {
                return new PredatorFactory();
            }
            default -> throw new RuntimeException("Factory controller can't create factory for that class");
        }
    }

    private int getAmount(String classEntity, Config config) {
        switch (classEntity) {
            case "simulation.entity.Rock" -> {
                return config.numberOfRocks;
            }
            case "simulation.entity.Tree" -> {
                return config.numberOfTrees;
            }
            case "simulation.entity.Grass" -> {
                return config.numberOfGrasses;
            }
            case "simulation.entity.creatures.Herbivore" -> {
                return config.numberOfHerbivores;
            }
            case "simulation.entity.creatures.Predator" -> {
                return config.numberOfPredators;
            }
            default -> throw new RuntimeException("Factory controller can't create factory for that class");
        }
    }
}
