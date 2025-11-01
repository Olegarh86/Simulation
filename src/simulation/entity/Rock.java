package simulation.entity;

public class Rock extends Entity {
//    private static final String NAME = "Rock";
    private static int rocksCount;
    private static final boolean movable = false;

    public Rock() {
        rocksCount++;
    }

    public static int getRocksCount() {
        return rocksCount;
    }

    public static int getCount() {
        return rocksCount;
    }

//    @Override
//    public String getName() {
//        return NAME;
//    }

    @Override
    public void decrementCountOfEntity() {
        rocksCount--;
    }

    @Override
    public boolean isMovable() {
        return movable;
    }
}
