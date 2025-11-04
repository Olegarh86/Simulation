package simulation.view.output;

import simulation.config.configFactories.DefaultConfigFactory;

public class ConsoleOutput implements Output{

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printCount(int countOfMoves) {
        System.out.println("Move number: " + countOfMoves);
    }

    @Override
    public void printGreetings() {
        System.out.println("In this program you can create a simulation of the real world, where predators \uD83D\uDC3A eat " +
                "herbivores \uD83D\uDC07 and herbivores eat grass \uD83C\uDF40.\nThe simulation can be started with default settings by " +
                "pressing 1 + enter, or you can set all simulation parameters manually by pressing 2 + enter");
    }

    @Override
    public void printMessageControls() {
        System.out.println("Press 'Enter + Space' - to stop the simulation, press any key to pause simulation, press key '1' - for resume the infinite simulation," +
                " press key 'Enter' - so that everyone makes one move");
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
                        %n%n""".formatted(DefaultConfigFactory.numberOfColumns, DefaultConfigFactory.numberOfLines,
        DefaultConfigFactory.numberOfRocks, DefaultConfigFactory.numberOfTrees,
        DefaultConfigFactory.numberOfGrasses, DefaultConfigFactory.numberOfHerbivores,
        DefaultConfigFactory.numberOfPredators, DefaultConfigFactory.herbivoresHp,
        DefaultConfigFactory.herbivoresSpeed, DefaultConfigFactory.predatorsHp,
        DefaultConfigFactory.predatorsSpeed, DefaultConfigFactory.predatorsAttackPower,
        DefaultConfigFactory.DELAY_BETWEEN_MOVES_IN_MILLISECONDS));

        for (int i = 0; i < configDefault.length(); i++) {
            System.out.print(configDefault.charAt(i));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
