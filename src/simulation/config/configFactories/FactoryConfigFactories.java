package simulation.config.configFactories;

import simulation.view.input.Input;
import simulation.view.output.Output;

public class FactoryConfigFactories {
    private final static String DEFAULT_CONFIG = "1";
    private final static String USER_CONFIG = "2";
    private final static String ERROR_CHANGE_CONFIG_FACTORY = """
            Incorrect symbol! Please, enter symbol for change configuration of the world: 
            '%s' - for default configuration or '%s' - for manual configuration"""
            .formatted(DEFAULT_CONFIG, USER_CONFIG);

    public ConfigFactory getConfigFactory(Input consoleInput, Output consoleOutput) {
        ConfigFactory configFactory = null;
        consoleOutput.greeting();

        while (configFactory == null) {
            String typeOfConfig = consoleInput.read();
            if (typeOfConfig.equals(DEFAULT_CONFIG)) {
                configFactory = new DefaultConfigFactory();
                consoleOutput.defaultConfig();
            } else if (typeOfConfig.equals(USER_CONFIG)) {
                configFactory = new UserConfigFactory(consoleInput, consoleOutput);
            } else {
                consoleOutput.message(ERROR_CHANGE_CONFIG_FACTORY);
            }
        }
        return configFactory;
    }
}
