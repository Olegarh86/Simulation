package simulation.map;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import simulation.entity.*;
import simulation.utils.*;

import java.util.*;

public class MapOfWorld {
    public Map<Coordinate, Entity> map = new HashMap<>();
    public BiMap<Coordinate, Entity> biMap = HashBiMap.create();
    public Set<Creature> setOfCreatures = new HashSet<>();

    public MapOfWorld() {
        for (int str = 1; str <= Config.getWeight(); str++) {
            for (int col = 1; col <= Config.getHeight(); col++) {
                Coordinate tempCoordinate = new Coordinate(str, col);
                map.put(tempCoordinate, new EmptyCell());
            }
        }
    }

    public void setEntity(Coordinate coordinate, Entity entity) {
        map.put(coordinate, entity);
    }

    public void setupStartPosition(int countOfRocks, int countOfTrees, int countOfGrass, int countOfHerbivores, int countOfPredators) {
        Coordinate randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
        Entity entity;
        Creature creature;
        int count = 0;
        while (count < countOfRocks) {
            while (!(map.get(randomCoordinate).getName().equals("EmptyCell"))) {
                randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
            }
            entity = new Rock();
            setEntity(randomCoordinate, entity);
            count++;
            randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
        }

        count = 0;
        while (count < countOfTrees) {
            while (!(map.get(randomCoordinate).getName().equals("EmptyCell"))) {
                randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
            }
            entity = new Tree();
            setEntity(randomCoordinate, entity);
            count++;
            randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
        }

        count = 0;
        while (count < countOfGrass) {
            while (!(map.get(randomCoordinate).getName().equals("EmptyCell"))) {
                randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
            }
            entity = new Grass();
            setEntity(randomCoordinate, entity);
            count++;
            randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
        }

        count = 0;
        while (count < countOfHerbivores) {
            while (!(map.get(randomCoordinate).getName().equals("EmptyCell"))) {
                randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
            }
            creature = new Herbivore(Config.getHerbivoresSpeed(), Config.getHerbivoresHp(), Config.getTargetForHerbivores());
            setEntity(randomCoordinate, creature);
            setOfCreatures.add(creature);
            count++;
            randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
        }

        count = 0;
        while (count < countOfPredators) {
            while (!(map.get(randomCoordinate).getName().equals("EmptyCell"))) {
                randomCoordinate = Coordinate.getRandomCoordinate(Config.getWeight(), Config.getHeight());
            }
            creature = new Predator(Config.getPredatorsSpeed(), Config.getPredatorsHp(), Config.getTargetForPredators(), Config.getPredatorsAttackPower());
            setEntity(randomCoordinate, creature);
            setOfCreatures.add(creature);
            count++;
        }
    }
}
