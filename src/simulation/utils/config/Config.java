package simulation.utils.config;

import simulation.utils.io.Input;
import simulation.utils.io.Output;

public class Config {
    public final static String defaultConfig = "1";
    public final static String userConfig = "2";
    public final static String ERROR_CHANGE_CONFIG_FACTORY = "Incorrect symbol! Please, enter symbol for change " +
            "configuration of the world: 1 - for default configuration or 2 - for manual configuration";
    public int numberOfColumns;
    public int numberOfLines;
    public int herbivoresHp;
    public int predatorsHp;
    public int herbivoresSpeed;
    public int predatorsSpeed;
    public int predatorsAttackPower;
    public int numberOfRocks;
    public int numberOfTrees;
    public int numberOfGrasses;
    public int numberOfHerbivores;
    public int numberOfPredators;
    public int delayBetweenMovesInMilliseconds;

    public static ConfigFactory changeConfigFactory(Input consoleInput, Output consoleOutput) {
        ConfigFactory configFactory = null;
        consoleOutput.greetings();

        while (configFactory == null) {
            String typeOfConfig = consoleInput.readString();
            if (typeOfConfig.equals(defaultConfig)) {
                configFactory = new DefaultConfigFactory();
            } else if (typeOfConfig.equals(userConfig)) {
                configFactory = new UserConfigFactory(consoleOutput);
            } else {
            consoleOutput.output(ERROR_CHANGE_CONFIG_FACTORY);
            }
        }
        return configFactory;
    }
}
