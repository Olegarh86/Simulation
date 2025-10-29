package simulation.utils.config;

import simulation.utils.io.Input;
import simulation.utils.io.Output;

public class Config {
    private final static String defaultConfig = "1";
    private final static String userConfig = "2";
    private final static String ERROR_CHANGE_CONFIG_FACTORY = "Incorrect symbol! Please, enter symbol for change " +
            "configuration of the world: 1 - for default configuration or 2 - for manual configuration";
    public int numberOfColumns;
    public int numberOfLines;
    public int numberOfRocks;
    public int numberOfTrees;
    public int numberOfGrasses;
    public int numberOfHerbivores;
    public int numberOfPredators;
    public int herbivoresHp;
    public int herbivoresSpeed;
    public int predatorsHp;
    public int predatorsSpeed;
    public int predatorsAttackPower;
    public int delayBetweenMovesInMilliseconds;

    public static ConfigFactory chooseConfigFactory(Input consoleInput, Output consoleOutput) {
        ConfigFactory configFactory = null;
        consoleOutput.printGreetings();

        while (configFactory == null) {
            String typeOfConfig = consoleInput.readString();
            if (typeOfConfig.equals(defaultConfig)) {
                configFactory = new DefaultConfigFactory();
                consoleOutput.printDefaultConfigWithDelay();
            } else if (typeOfConfig.equals(userConfig)) {
                configFactory = new UserConfigFactory(consoleInput, consoleOutput);
            } else {
            consoleOutput.printMessage(ERROR_CHANGE_CONFIG_FACTORY);
            }
        }
        return configFactory;
    }
}
