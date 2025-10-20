package simulation.utils;

public class Config {
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

    public static ConfigFactory changeConfigFactory(ConsoleScanner consoleScanner) {
        ConfigFactory configFactory = null;

        while (configFactory == null) {
            String typeOfConfig = consoleScanner.readString();
            if (typeOfConfig.equals("1")) {
                configFactory = new DefaultConfigFactory();
            } else if (typeOfConfig.equals("2")) {
                configFactory = new UserConfigFactory();
            } else {
            System.out.println("Incorrect symbol! Please, enter symbol for change configuration of the world: " +
                    "1 - for default configuration or 2 - for manual configuration");
            }
        }
        return configFactory;
    }
}
