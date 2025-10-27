package simulation.entity;

import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

public class Grass extends Entity {
    private static final String NAME = "Grass";
    private static int grassCount = 0;

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
}
