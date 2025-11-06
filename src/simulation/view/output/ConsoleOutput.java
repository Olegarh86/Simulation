package simulation.view.output;

import simulation.config.configFactories.DefaultConfigFactory;
import simulation.view.input.Input;

public class ConsoleOutput implements Output{
    private final Input input;

    public ConsoleOutput(Input input) {
        this.input = input;
    }

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
        System.out.println("Press 'Enter + Space' - to stop this simulation, press any key to pause simulation, press key '1' - for resume the infinite simulation," +
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
                        width of the world: %d
                        height of the world: %d
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
                        
                        Press 'Enter' to start""".formatted(DefaultConfigFactory.GAME_MAP_WIDTH, DefaultConfigFactory.GAME_MAP_HEIGHT,
        DefaultConfigFactory.NUMBER_OF_ROCKS, DefaultConfigFactory.NUMBER_OF_TREES,
        DefaultConfigFactory.NUMBER_OF_GRASSES, DefaultConfigFactory.NUMBER_OF_HERBIVORES,
        DefaultConfigFactory.NUMBER_OF_PREDATORS, DefaultConfigFactory.HERBIVORES_HP,
        DefaultConfigFactory.HERBIVORES_SPEED, DefaultConfigFactory.PREDATORS_HP,
        DefaultConfigFactory.PREDATORS_SPEED, DefaultConfigFactory.PREDATORS_ATTACK_POWER,
        DefaultConfigFactory.DELAY_BETWEEN_MOVES_IN_MILLISECONDS));

        System.out.println(configDefault);
        input.read();
    }
}
