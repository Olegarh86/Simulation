package simulation.utils;

import simulation.entity.Entity;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.Map;

public class BaseSimulationRenderer implements Renderer {
    private static final String emptyCell = "\u2B1C";
    private static final String rock = "\uD83D\uDC8E";
    private static final String tree = "\uD83C\uDF31";
    private static final String grass = "\uD83C\uDF40";
    private static final String rabbit = "\uD83D\uDC07";
    private static final String wolf = "\uD83D\uDC3A";

    public BaseSimulationRenderer() {}

    @Override
    public String getSprite (Entity entity) {
        return switch (entity.getName()) {
            case "Tree" -> tree;
            case "Rock" -> rock;
            case "Grass" -> grass;
            case "Herbivore" -> rabbit;
            case "Predator" -> wolf;
            case "EmptyCell" -> emptyCell;
            default -> throw new RuntimeException("This entity haven`t sprite");
        };
    }
}
