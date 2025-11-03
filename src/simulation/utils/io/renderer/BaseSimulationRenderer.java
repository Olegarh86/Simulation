package simulation.utils.io.renderer;

import simulation.entity.*;
import simulation.entity.creatures.Herbivore;
import simulation.entity.creatures.Predator;
import simulation.utils.config.Config;
import simulation.utils.io.Output;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

public class BaseSimulationRenderer implements Renderer {
    private static final String EMPTY_CELL_SPRITE = "\u2B1C";
    private static final String ROCK_SPRITE = "\uD83D\uDC8E";
    private static final String TREE_SPRITE = "\uD83C\uDF31";
    private static final String GRASS_SPRITE = "\uD83C\uDF40";
    private static final String HERBIVORE_SPRITE = "\u001B[43m\uD83D\uDC07\u001B[0m";
    private static final String PREDATOR_SPRITE = "\uD83D\uDC3A";
    private static final String THIS_ENTITY_HAVEN_T_SPRITE = "This entity haven`t sprite";
    private static final String INTERVAL = " ";
    private final Output output;


    public BaseSimulationRenderer(Output output) {
        this.output = output;
    }

    @Override
    public void draw(Config config, MapOfWorld world) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < config.numberOfLines; i++) {
            for (int j = 0; j < config.numberOfColumns; j++) {
                    stringBuilder.append(getSprite(world.getEntity(Coordinate.getCoordinate(i, j)).orElse(null)));
                    stringBuilder.append(INTERVAL);
            }
            stringBuilder.append("\n");
        }
        output.printMessage(stringBuilder + "\n");
    }

    @Override
    public String getSprite (Object entity) {
        return switch (entity) {
            case Tree t -> TREE_SPRITE;
            case Rock r -> ROCK_SPRITE;
            case Grass g -> GRASS_SPRITE;
            case Herbivore h -> HERBIVORE_SPRITE;
            case Predator p -> PREDATOR_SPRITE;
            case null -> EMPTY_CELL_SPRITE;
            default -> throw new RuntimeException(THIS_ENTITY_HAVEN_T_SPRITE);
        };
    }
}
