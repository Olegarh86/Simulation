package simulation.utils;

import simulation.entity.Entity;
import simulation.map.Coordinate;
import simulation.map.MapOfWorld;

import java.util.Map;

public class SimulationRenderer {
    public static final String RESET = "\u001B[0m";
    public static final String RED   = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW= "\u001B[33m";
    public static final String BLUE  = "\u001B[34m";
    private static final String emptyCell = "\u2B1C";
    private static final String rock = "\uD83D\uDC8E";
    private static final String tree = "\uD83C\uDF31";
    private static final String grass = "\uD83C\uDF40";
    private static final String rabbit = "\uD83D\uDC07";
    private static final String wolf = "\uD83D\uDC3A";

    private SimulationRenderer() {}

    public static void draw(MapOfWorld map) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Map.Entry<Coordinate, Entity> entry : map.biMap.entrySet()) {
            if (count < Config.getWeight()) {
                stringBuilder.append(getSprite(entry.getValue())).append(" ");
                count++;
            } else {
                System.out.println(stringBuilder);
                count = 1;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getSprite(entry.getValue())).append(" ");
            }
        }
        System.out.println(stringBuilder + "\n");
    }

    public static String getSprite (Entity entity) {
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
