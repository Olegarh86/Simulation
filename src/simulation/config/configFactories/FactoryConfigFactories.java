package simulation.config.configFactories;

import simulation.view.input.Input;
import simulation.view.output.Output;

public class FactoryConfigFactories {
    public ConfigFactory configFactory;
    private final static String DEFAULT_CONFIG = "1";
    private final static String USER_CONFIG = "2";
    private final static String ERROR_CHANGE_CONFIG_FACTORY = "Incorrect symbol! Please, enter symbol for change " +
            "configuration of the world: 1 - for default configuration or 2 - for manual configuration";

    public ConfigFactory getConfigFactory(Input consoleInput, Output consoleOutput) {
        configFactory = null;
        consoleOutput.printGreetings();

        while (configFactory == null) {
            String typeOfConfig = consoleInput.readInput();
            if (typeOfConfig.equals(DEFAULT_CONFIG)) {
                configFactory = new DefaultConfigFactory();
                consoleOutput.printDefaultConfigWithDelay();
            } else if (typeOfConfig.equals(USER_CONFIG)) {
                configFactory = new UserConfigFactory(consoleInput, consoleOutput);
            } else {
                consoleOutput.printMessage(ERROR_CHANGE_CONFIG_FACTORY);
            }
        }
        return configFactory;
    }
}
