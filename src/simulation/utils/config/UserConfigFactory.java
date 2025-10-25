package simulation.utils.config;

import simulation.utils.io.Output;

import java.util.Scanner;

public class UserConfigFactory implements ConfigFactory {
    private final static String ERROR = "Incorrect symbol, please, try again";
    private final static String HOW_MANY_COLUMNS = "How many columns will there be? From %d to %d ";
    private final static String HOW_MANY_LINES = "How many lines will there be? From %d to %d ";
    private final static String HOW_MANY_HERBIVORES = "How many herbivores will there be? From %d to %d ";
    private final static String HOW_MANY_PREDATORS = "How many predators will there be? From %d to %d ";
    private final static String HOW_MUCH_GRASS = "How much grass will there be? From %d to %d ";
    private final static String HOW_MANY_STONES = "How many stones will there be? From %d to %d ";
    private final static String HOW_MANY_TREES = "How many trees will there be? From %d to %d ";
    private final static String HOW_MUCH_HERBIVORES_HP = "How much health will herbivores have? From %d to %d ";
    private final static String HOW_MUCH_HERBIVORES_SPEED = "How much speed will herbivores have? From %d to %d ";
    private final static String HOW_MUCH_PREDATORS_HP = "How much health will predators have? From %d to %d ";
    private final static String HOW_MUCH_PREDATORS_SPEED = "How much speed will predators have? From %d to %d ";
    private final static String HOW_MUCH_PREDATORS_ATTACK_POWER = "How much attack power will predators have? From %d to %d ";
    private final static String DELAY = "What is the delay between moves? From %d to %d ";
    private final static int min = 0;
    private final static int max = 30;
    private final static int minColumnsAndLines = 1;
    private final static int maxColumnsAndLines = 68;
    private final static int minDelay = 1;
    private final static int maxDelay = Integer.MAX_VALUE;
    Output consoleOutput;

    public UserConfigFactory(Output consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    @Override
    public Config get() {
        Config config = new Config();
        consoleOutput.messageChangeConfig();
        config.numberOfLines = input(HOW_MANY_COLUMNS, minColumnsAndLines, maxColumnsAndLines);
        config.numberOfColumns = input(HOW_MANY_LINES, minColumnsAndLines, maxColumnsAndLines);
        config.numberOfHerbivores = input(HOW_MANY_HERBIVORES, min, max);
        config.numberOfPredators = input(HOW_MANY_PREDATORS, min, max);
        config.numberOfGrasses = input(HOW_MUCH_GRASS, min, max);
        config.numberOfRocks = input(HOW_MANY_STONES, min, max);
        config.numberOfTrees = input(HOW_MANY_TREES, min, max);
        config.herbivoresHp = input(HOW_MUCH_HERBIVORES_HP, min, max);
        config.herbivoresSpeed = input(HOW_MUCH_HERBIVORES_SPEED, min, max);
        config.predatorsHp = input(HOW_MUCH_PREDATORS_HP, min, max);
        config.predatorsSpeed = input(HOW_MUCH_PREDATORS_SPEED, min, max);
        config.predatorsAttackPower = input(HOW_MUCH_PREDATORS_ATTACK_POWER, min, max);
        config.delayBetweenMovesInMilliseconds = input(DELAY, minDelay, maxDelay);
        return config;
    }

    private int input(String message, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf(message, min, max);
            String inputSymbol = scanner.nextLine();

            if (isInteger(inputSymbol)) {
                int value = Integer.parseInt(inputSymbol);

                if (isValid(value, min, max)) {
                    return value;
                }
            }
            System.out.println(ERROR);
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
