package simulation.utils.io.renderer;

import simulation.entity.Entity;

public class BaseSimulationRenderer implements Renderer {
    private static final String emptyCellSprite = "\u2B1C";
    private static final String rockSprite = "\uD83D\uDC8E";
    private static final String treeSprite = "\uD83C\uDF31";
    private static final String grassSprite = "\uD83C\uDF40";
    private static final String herbivoreSprite = "\u001B[41m\uD83D\uDC07\u001B[0m";
    private static final String wolfSprite = "\uD83D\uDC3A";
    private static final String treeName = "Tree";
    private static final String rockName = "Rock";
    private static final String grassName = "Grass";
    private static final String herbivoreName = "Herbivore";
    private static final String predatorName = "Predator";
    private static final String emptyCellName = "EmptyCell";
    private static final String itIsNotEntity = "This entity haven`t sprite";

    public BaseSimulationRenderer() {}

    @Override
    public String getSprite (Entity entity) {
        return switch (entity.getName()) {
            case treeName -> treeSprite;
            case rockName -> rockSprite;
            case grassName -> grassSprite;
            case herbivoreName -> herbivoreSprite;
            case predatorName -> wolfSprite;
            case emptyCellName -> emptyCellSprite;
            default -> throw new RuntimeException(itIsNotEntity);
        };
    }
}
