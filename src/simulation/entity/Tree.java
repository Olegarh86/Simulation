package simulation.entity;

public class Tree extends Entity {
    private static final String NAME = "Tree";
    private static int treesCount;
    private static final boolean movable = false;

    public Tree() {
        treesCount++;
    }

    public static int getTreesCount() {
        return treesCount;
    }

    public static int getCount() {
        return treesCount;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void decrementCountOfEntity() {
        treesCount--;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }
}
