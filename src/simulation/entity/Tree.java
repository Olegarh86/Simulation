package simulation.entity;

public class Tree extends Entity{

    @Override
    public String getName() {
        return "Tree";
    }

    public boolean getAvailableToMove() {
        return false;
    }
}
