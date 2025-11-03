package simulation.config.configFactories;

import simulation.config.Config;
import simulation.view.input.Input;
import simulation.view.output.Output;

public class UserConfigFactory implements ConfigFactory {
    private final static String ERROR = "Incorrect symbol, please, try again";
    private final static String HOW_MANY_COLUMNS = "How many columns will there be? From %d to %d ";
    private final static String HOW_MANY_LINES = "How many lines will there be? From %d to %d ";
    private final static String HOW_MANY_ROCKS = "How many rocks will there be? From %d to %d ";
    private final static String HOW_MANY_TREES = "How many trees will there be? From %d to %d ";
    private final static String HOW_MUCH_GRASS = "How much grasses will there be? From %d to %d ";
    private final static String HOW_MANY_HERBIVORES = "How many herbivores will there be? From %d to %d ";
    private final static String HOW_MANY_PREDATORS = "How many predators will there be? From %d to %d ";
    private final static String HOW_MUCH_HERBIVORES_HP = "How much health will herbivores have? From %d to %d ";
    private final static String HOW_MUCH_HERBIVORES_SPEED = "How much speed will herbivores have? From %d to %d ";
    private final static String HOW_MUCH_PREDATORS_HP = "How much health will predators have? From %d to %d ";
    private final static String HOW_MUCH_PREDATORS_SPEED = "How much speed will predators have? From %d to %d ";
    private final static String HOW_MUCH_PREDATORS_ATTACK_POWER = "How much attack power will predators have? From %d to %d ";
    private final static String DELAY = "What is the delay between moves? From %d to %d ";
    private final static int MIN = 0;
    private final static int MAX = 30;
    private final static int MIN_COLUMNS_AND_LINES = 1;
    private final static int MAX_COLUMNS_AND_LINES = 68;
    private final static int MIN_DELAY = 1;
    private final static int MAX_VALUE = Integer.MAX_VALUE;
    private final Output consoleOutput;
    private final Input consoleInput;

    protected UserConfigFactory(Input consoleInput, Output consoleOutput) {
        this.consoleOutput = consoleOutput;
        this.consoleInput = consoleInput;
    }

    @Override
    public Config getConfig() {
        Config config = new Config();
        consoleOutput.printMessageChangeConfig();
        config.numberOfLines = input(HOW_MANY_COLUMNS, MIN_COLUMNS_AND_LINES, MAX_COLUMNS_AND_LINES);
        config.numberOfColumns = input(HOW_MANY_LINES, MIN_COLUMNS_AND_LINES, MAX_COLUMNS_AND_LINES);
        config.numberOfRocks = input(HOW_MANY_ROCKS, MIN, MAX);
        config.numberOfTrees = input(HOW_MANY_TREES, MIN, MAX);
        config.numberOfGrasses = input(HOW_MUCH_GRASS, MIN, MAX);
        config.numberOfHerbivores = input(HOW_MANY_HERBIVORES, MIN, MAX);
        config.numberOfPredators = input(HOW_MANY_PREDATORS, MIN, MAX);
        config.herbivoresHp = input(HOW_MUCH_HERBIVORES_HP, MIN, MAX);
        config.herbivoresSpeed = input(HOW_MUCH_HERBIVORES_SPEED, MIN, MAX);
        config.predatorsHp = input(HOW_MUCH_PREDATORS_HP, MIN, MAX);
        config.predatorsSpeed = input(HOW_MUCH_PREDATORS_SPEED, MIN, MAX);
        config.predatorsAttackPower = input(HOW_MUCH_PREDATORS_ATTACK_POWER, MIN, MAX);
        config.delayBetweenMovesInMilliseconds = input(DELAY, MIN_DELAY, MAX_VALUE);
        return config;
    }

    private int input(String message, int min, int max) {
        String inputSymbol;
        int value;
        while (true) {
            consoleOutput.printMessage(message.formatted(min, max));
            inputSymbol = consoleInput.readInput();
            if (isInteger(inputSymbol)) {
                value = Integer.parseInt(inputSymbol);

                if (isValid(value, min, max)) {
                    return value;
                }
            }
            consoleOutput.printMessage(ERROR);
        }
    }

    private boolean isInteger(String inputSymbol) {
        try {
            Integer.parseInt(inputSymbol);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValid(int value, int min, int max) {
        return value >= min && value <= max;
    }

}
