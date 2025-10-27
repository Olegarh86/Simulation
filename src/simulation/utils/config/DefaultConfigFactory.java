package simulation.utils.config;

public class DefaultConfigFactory implements ConfigFactory {
    public final static int NUMBER_OF_COLUMNS = 10;
    public final static int NUMBER_OF_LINES = 10;
    public final static int NUMBER_OF_ROCKS = 10;
    public final static int NUMBER_OF_TREES = 10;
    public final static int NUMBER_OF_GRASSES = 15;
    public final static int NUMBER_OF_HERBIVORES = 10;
    public final static int NUMBER_OF_PREDATORS = 10;
    public final static int HERBIVORES_HP = 10;
    public final static int HERBIVORES_SPEED = 1;
    public final static int PREDATORS_HP = 10;
    public final static int PREDATORS_SPEED = 1;
    public final static int PREDATORS_ATTACK_POWER = 5;
    public final static int DELAY_BETWEEN_MOVES_IN_MILLISECONDS = 100;

    @Override
    public Config get() {
        Config config = new Config();
        config.numberOfColumns = NUMBER_OF_COLUMNS;
        config.numberOfLines = NUMBER_OF_LINES;
        config.numberOfRocks = NUMBER_OF_ROCKS;
        config.numberOfTrees = NUMBER_OF_TREES;
        config.numberOfGrasses = NUMBER_OF_GRASSES;
        config.numberOfHerbivores = NUMBER_OF_HERBIVORES;
        config.numberOfPredators = NUMBER_OF_PREDATORS;
        config.herbivoresHp = HERBIVORES_HP;
        config.herbivoresSpeed = HERBIVORES_SPEED;
        config.predatorsHp = PREDATORS_HP;
        config.predatorsSpeed = PREDATORS_SPEED;
        config.predatorsAttackPower = PREDATORS_ATTACK_POWER;
        config.delayBetweenMovesInMilliseconds = DELAY_BETWEEN_MOVES_IN_MILLISECONDS;
        return config;
    }
}
