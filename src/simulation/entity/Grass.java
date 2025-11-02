package simulation.entity;

public class Grass extends Entity {
    private static final boolean movable = false;

    @Override
    public boolean isMovable() {
        return movable;
    }
}
