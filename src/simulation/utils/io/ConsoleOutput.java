package simulation.utils.io;

import simulation.utils.config.DefaultConfigFactory;

public class ConsoleOutput implements Output{

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printCount(int countOfMoves) {
        System.out.println("Number of turns made by creatures: " + countOfMoves);
    }

    @Override
    public void printGreetings() {
        System.out.println("In this program you can create a simulation of the real world, where predators eat " +
                "herbivores and herbivores eat grass.\nThe simulation can be started with default settings by " +
                "pressing 1 + enter, or you can set all simulation parameters manually by pressing 2 + enter");
    }

    @Override
    public void printMessageControls() {
        System.out.println("Press key: 1 - for pause the simulation, 2 - for resume the infinite simulation, " +
                "Enter - all creatures make 1 turn, Enter + Space - for end the simulation.\n");
    }

    @Override
    public void printMessageChangeConfig() {
        System.out.println("Please, enter the world parameters in integers.");
    }

    @Override
    public void printDefaultConfigWithDelay() {
        String configDefault = ("""
                Ok! default configuration:
                number of columns: %d
                number of lines: %d
                number of rocks: %d
                number of trees: %d
                number of grasses: %d
                number of herbivores: %d
                number of predators: %d
                herbivores hp: %d
                herbivores speed: %d
                predators hp: %d
                predators speed: %d
                predators attack power: %d
                delay between moves: %d milliseconds
                start simulation after:
                3
                2
                1
                START!
                %n""".formatted(DefaultConfigFactory.NUMBER_OF_COLUMNS, DefaultConfigFactory.NUMBER_OF_LINES,
                DefaultConfigFactory.NUMBER_OF_ROCKS, DefaultConfigFactory.NUMBER_OF_TREES,
                DefaultConfigFactory.NUMBER_OF_GRASSES, DefaultConfigFactory.NUMBER_OF_HERBIVORES,
                DefaultConfigFactory.NUMBER_OF_PREDATORS, DefaultConfigFactory.HERBIVORES_HP,
                DefaultConfigFactory.HERBIVORES_SPEED, DefaultConfigFactory.PREDATORS_HP,
                DefaultConfigFactory.PREDATORS_SPEED, DefaultConfigFactory.PREDATORS_ATTACK_POWER,
                DefaultConfigFactory.DELAY_BETWEEN_MOVES_IN_MILLISECONDS));

        for (int i = 0; i < configDefault.length(); i++) {
            System.out.print(configDefault.charAt(i));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
