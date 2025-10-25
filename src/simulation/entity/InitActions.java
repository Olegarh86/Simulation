package simulation.entity;

import simulation.map.Coordinate;
import simulation.map.MapOfWorld;
import simulation.utils.config.Config;

import java.util.Set;

public class InitActions implements Actions{
    MapOfWorld map;
    Config config;
    CreatorOfRocks creatorOfRocks = new CreatorOfRocks();
    CreatorOfTrees creatorOfTrees = new CreatorOfTrees();
    CreatorOfGrasses creatorOfGrasses = new CreatorOfGrasses();
    CreatorOfHerbivores creatorOfHerbivores = new CreatorOfHerbivores();
    CreatorOfPredators creatorOfPredators = new CreatorOfPredators();

    public InitActions(MapOfWorld map, Config config) {
        this.map = map;
        this.config = config;
    }

    @Override
    public void execute() {
        if (Rock.rocksCount < 1) {
            setEntitiesToRandomCoordinate(map, config, creatorOfRocks);
        }
        if (Tree.treesCount < 1) {
            setEntitiesToRandomCoordinate(map, config, creatorOfTrees);
        }
        if (Herbivore.herbivoresCount < 1) {
            setEntitiesToRandomCoordinate(map, config, creatorOfHerbivores);
        }
        if (Predator.predatorsCount < 1) {
            setEntitiesToRandomCoordinate(map, config, creatorOfPredators);
        }
        if (Grass.grassCount < 1) {
            setEntitiesToRandomCoordinate(map, config, creatorOfGrasses);
        }
    }

    private static void setEntitiesToRandomCoordinate(MapOfWorld map, Config config, Creator creator) {
        Set<Entity> entities = creator.createMultipleEntities(map, config);
        Coordinate randomCoordinate;
        for (Entity entity : entities) {
            randomCoordinate = chooseEmptyRandomCoordinate(map, config);
            entity.setEntity(map, randomCoordinate);
        }
    }

    private static Coordinate chooseEmptyRandomCoordinate(MapOfWorld map, Config config) {
        Coordinate randomCoordinate = Coordinate.getRandomCoordinate(config);
        int count = 0;

        while (!(map.biMap.get(randomCoordinate).getName().equals("EmptyCell"))) {
            randomCoordinate = Coordinate.getRandomCoordinate(config);
            count++;

            if (count > (config.numberOfLines * config.numberOfColumns)) {
                throw new RuntimeException("Empty cells is not available "); //TODO
            }
        }
        return randomCoordinate;
    }
}
