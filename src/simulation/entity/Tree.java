package simulation.entity;

import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

public class Tree extends Entity {
    private static final String NAME = "Tree";
    private static int treesCount = 0;

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
}
