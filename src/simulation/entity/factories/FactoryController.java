package simulation.entity.factories;

import simulation.entity.Entity;

public class FactoryController {
    public EntityFactory entityFactory;


    public FactoryController(Class<? extends Entity> classEntity) {
        this.entityFactory = getEntityFactory(classEntity.getName());
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
}
