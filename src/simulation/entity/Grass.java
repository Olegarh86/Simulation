package simulation.entity;

public class Grass extends Entity {
    private static final String NAME = "Grass";
    private static int grassCount;
    private static final boolean movable = false;

    public Grass() {
        grassCount++;
    }

    public static int getGrassCount() {
        return grassCount;
    }

    public static int getCount() {
        return grassCount;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void decrementCountOfEntity() {
        grassCount--;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }
}
