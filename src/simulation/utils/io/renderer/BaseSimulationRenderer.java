package simulation.utils.io.renderer;

import simulation.entity.Entity;
import simulation.utils.config.Config;
import simulation.utils.io.Output;
import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

import java.util.Map;

public class BaseSimulationRenderer implements Renderer {
    private static final String EMPTY_CELL_SPRITE = "\u2B1C";
    private static final String ROCK_SPRITE = "\uD83D\uDC8E";
    private static final String TREE_SPRITE = "\uD83C\uDF31";
    private static final String GRASS_SPRITE = "\uD83C\uDF40";
    private static final String HERBIVORE_SPRITE = "\u001B[43m\uD83D\uDC07\u001B[0m";
    private static final String WOLF_SPRITE = "\uD83D\uDC3A";
    private static final String TREE_NAME = "Tree";
    private static final String ROCK_NAME = "Rock";
    private static final String GRASS_NAME = "Grass";
    private static final String HERBIVORE_NAME = "Herbivore";
    private static final String PREDATOR_NAME = "Predator";
    private static final String EMPTY_CELL_NAME = "EmptyCell";
    private static final String THIS_ENTITY_HAVEN_T_SPRITE = "This entity haven`t sprite";
    private static final String INTERVAL = " ";
    private final Output output;


    public BaseSimulationRenderer(Output output) {
        this.output = output;
    }

    public void draw(Config config, MapOfWorld world) {
        StringBuilder stringBuilder = new StringBuilder();
//        int count = 0;
        for (int i = 0; i < config.numberOfLines; i++) {
            for (int j = 0; j < config.numberOfColumns; j++) {
                stringBuilder.append(getSprite(world.getEntity(Coordinate.getCoordinate(i, j)))).append(INTERVAL);

            }
            stringBuilder.append("\n");
        }
//        for (Map.Entry<Coordinate, Entity> entry : world.getCoordinatesEntities().entrySet()) {
//            if (count < config.numberOfLines) {
//                stringBuilder.append(getSprite(entry.getValue())).append(INTERVAL);
//                count++;
//            } else {
//                output.printMessage(stringBuilder.toString());
//                count = 1;
//                stringBuilder = new StringBuilder();
//                stringBuilder.append(getSprite(entry.getValue())).append(INTERVAL);
//            }
//        }
        output.printMessage(stringBuilder + "\n");
    }

    @Override
    public String getSprite (Entity entity) {
        return switch (entity.getName()) {
            case TREE_NAME -> TREE_SPRITE;
            case ROCK_NAME -> ROCK_SPRITE;
            case GRASS_NAME -> GRASS_SPRITE;
            case HERBIVORE_NAME -> HERBIVORE_SPRITE;
            case PREDATOR_NAME -> WOLF_SPRITE;
            case EMPTY_CELL_NAME -> EMPTY_CELL_SPRITE;
            default -> throw new RuntimeException(THIS_ENTITY_HAVEN_T_SPRITE);
        };
    }
}
