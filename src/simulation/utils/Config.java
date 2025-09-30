package simulation.utils;

public class Config {
    private static final int HEIGHT = 10;
    private static final int WEIGHT = 10;
    private static final int HERBIVORES_SPEED = 1;
    private static final int HERBIVORES_HP = 2;
    private static final String TARGET_FOR_HERBIVORES = "Grass";
    private static final String TARGET_FOR_PREDATORS = "Herbivore";
    private static final int PREDATORS_SPEED = 1;
    private static final int PREDATORS_HP = 2;
    private static final int PREDATORS_ATTACK_POWER = 1;
    private static final int NUMBER_OF_ROCKS = 0;
    private static final int NUMBER_OF_TREES = 0;
    private static final int NUMBER_OF_GRASSES = 5;
    private static final int NUMBER_OF_HERBIVORES = 2;
    private static final int NUMBER_OF_PREDATORS = 2;

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getWeight() {
        return WEIGHT;
    }

    public static int getHerbivoresSpeed() {
        return HERBIVORES_SPEED;
    }

    public static int getHerbivoresHp() {
        return HERBIVORES_HP;
    }

    public static int getPredatorsSpeed() {
        return PREDATORS_SPEED;
    }

    public static int getPredatorsHp() {
        return PREDATORS_HP;
    }

    public static int getNumberOfRocks() {
        return NUMBER_OF_ROCKS;
    }

    public static int getNumberOfTrees() {
        return NUMBER_OF_TREES;
    }

    public static int getNumberOfGrasses() {
        return NUMBER_OF_GRASSES;
    }

    public static int getNumberOfHerbivores() {
        return NUMBER_OF_HERBIVORES;
    }

    public static int getNumberOfPredators() {
        return NUMBER_OF_PREDATORS;
    }

    public static String getTargetForHerbivores() {
        return TARGET_FOR_HERBIVORES;
    }

    public static String getTargetForPredators() {
        return TARGET_FOR_PREDATORS;
    }

    public static int getPredatorsAttackPower() {
        return PREDATORS_ATTACK_POWER;
    }
}
