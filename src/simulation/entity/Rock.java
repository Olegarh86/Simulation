package simulation.entity;

import simulation.world.Coordinate;
import simulation.world.MapOfWorld;

public class Rock extends Entity {
    private static final String NAME = "Rock";
    private static int rocksCount = 0;

    public Rock() {
        rocksCount++;
    }

    public static int getRocksCount() {
        return rocksCount;
    }

    public static int getCount() {
        return rocksCount;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean cellAvailableToMove(MapOfWorld world, Coordinate coordinate) {
        return false;
    }

    @Override
    public void decrementCountOfEntity() {
        rocksCount--;
    }
}
