package simulation.utils;

import java.util.Scanner;

public class UserConfigFactory implements ConfigFactory{
    private final static int min = 0;
    private final static int max = 30;
    private final static int minColumnsAndLines = 1;
    private final static int maxColumnsAndLines = 68;
    private final static int minDelay = 1;
    private final static int maxDelay = Integer.MAX_VALUE;

    @Override
    public Config get() {
        Config config = new Config();
        System.out.println("Please, enter the world parameters in integers.");
        String error = "Incorrect symbol, please, try again";
        config.numberOfLines = input("How many columns will there be? From %d to %d ", error, minColumnsAndLines, maxColumnsAndLines);
        config.numberOfColumns = input("How many lines will there be? From %d to %d ", error, minColumnsAndLines, maxColumnsAndLines);
        config.numberOfHerbivores = input("How many herbivores will there be? From %d to %d ", error, min, max);
        config.numberOfPredators = input("How many predators will there be? From %d to %d ", error, min, max);
        config.numberOfGrasses = input("How many grasses will there be? From %d to %d ", error, min, max);
        config.numberOfRocks = input("How many stones will there be? From %d to %d ", error, min, max);
        config.numberOfTrees = input("How many trees will there be? From %d to %d ", error, min, max);
        config.herbivoresHp = input("How mush health will herbivores have? From %d to %d ", error, min, max);
        config.herbivoresSpeed = input("How mush speed will herbivores have? From %d to %d ", error, min, max);
        config.predatorsHp = input("How mush health will predators have? From %d to %d ", error, min, max);
        config.predatorsSpeed = input("How mush speed will predators have? From %d to %d ", error, min, max);
        config.predatorsAttackPower = input("How mush attack power will predators have? From %d to %d ", error, min, max);
        config.delayBetweenMovesInMilliseconds = input("What is the delay between moves? From %d to %d ", error, minDelay, maxDelay);
        return config;
    }

    private int input(String message, String error, int min, int max) {
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
            System.out.println(error);
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
