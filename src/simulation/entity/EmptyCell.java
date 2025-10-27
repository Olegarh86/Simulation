package simulation.entity;

public class EmptyCell extends Entity {
    private static final String NAME = "EmptyCell";
    public static int emptyCellsCount = 0;

    public EmptyCell() {
        emptyCellsCount++;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void decrementCountOfEntity() {
        emptyCellsCount--;
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
