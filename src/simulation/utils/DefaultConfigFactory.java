package simulation.utils;

public class DefaultConfigFactory implements ConfigFactory{
    private final static int NUMBER_OF_COLUMNS = 10;
    private final static int NUMBER_OF_LINES = 10;
    private final static int HERBIVORES_HP = 10;
    private final static int PREDATORS_HP = 10;
    private final static int HERBIVORES_SPEED = 1;
    private final static int PREDATORS_SPEED = 1;
    private final static int PREDATORS_ATTACK_POWER = 5;
    private final static int NUMBER_OF_ROCKS = 5;
    private final static int NUMBER_OF_TREES = 10;
    private final static int NUMBER_OF_GRASSES = 10;
    private final static int NUMBER_OF_HERBIVORES = 10;
    private final static int NUMBER_OF_PREDATORS = 10;
    private final static int DELAY_BETWEEN_MOVES_IN_MILLISECONDS = 1000;

    @Override
    public Config get() {
            Config config = new Config();
            config.numberOfColumns = NUMBER_OF_COLUMNS;
            config.numberOfLines = NUMBER_OF_LINES;
            config.herbivoresHp = HERBIVORES_HP;
            config.predatorsHp = PREDATORS_HP;
            config.herbivoresSpeed = HERBIVORES_SPEED;
            config.predatorsSpeed = PREDATORS_SPEED;
            config.predatorsAttackPower = PREDATORS_ATTACK_POWER;
            config.numberOfRocks = NUMBER_OF_ROCKS;
            config.numberOfTrees = NUMBER_OF_TREES;
            config.numberOfGrasses = NUMBER_OF_GRASSES;
            config.numberOfHerbivores = NUMBER_OF_HERBIVORES;
            config.numberOfPredators = NUMBER_OF_PREDATORS;
            config.delayBetweenMovesInMilliseconds = DELAY_BETWEEN_MOVES_IN_MILLISECONDS;
            return config;
    }
}
