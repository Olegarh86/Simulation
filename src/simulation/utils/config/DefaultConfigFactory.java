package simulation.utils.config;

public class DefaultConfigFactory implements ConfigFactory {
    public final static int numberOfColumns = 10;
    public final static int numberOfLines = 10;
    public final static int numberOfRocks = 10;
    public final static int numberOfTrees = 10;
    public final static int numberOfGrasses = 15;
    public final static int numberOfHerbivores = 10;
    public final static int numberOfPredators = 10;
    public final static int herbivoresHp = 10;
    public final static int herbivoresSpeed = 1;
    public final static int predatorsHp = 10;
    public final static int predatorsSpeed = 1;
    public final static int predatorsAttackPower = 5;
    public final static int DELAY_BETWEEN_MOVES_IN_MILLISECONDS = 100;

    @Override
    public Config getConfig() {
        Config config = new Config();
        config.numberOfColumns = numberOfColumns;
        config.numberOfLines = numberOfLines;
        config.numberOfRocks = numberOfRocks;
        config.numberOfTrees = numberOfTrees;
        config.numberOfGrasses = numberOfGrasses;
        config.numberOfHerbivores = numberOfHerbivores;
        config.numberOfPredators = numberOfPredators;
        config.herbivoresHp = herbivoresHp;
        config.herbivoresSpeed = herbivoresSpeed;
        config.predatorsHp = predatorsHp;
        config.predatorsSpeed = predatorsSpeed;
        config.predatorsAttackPower = predatorsAttackPower;
        config.delayBetweenMovesInMilliseconds = DELAY_BETWEEN_MOVES_IN_MILLISECONDS;
        return config;
    }
}
