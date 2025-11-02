package simulation.entity;

public class Tree extends Entity {
    private static final boolean movable = false;

    @Override
    public boolean isMovable() {
        return movable;
    }
}
