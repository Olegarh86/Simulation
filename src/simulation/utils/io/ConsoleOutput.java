package simulation.utils.io;

public class ConsoleOutput implements Output{

    @Override
    public void output(String message) {
        System.out.println(message);
    }

    @Override
    public void output(int count) {
        System.out.println("Number of turns made by creatures: " + String.valueOf(count));
    }

    @Override
    public void greetings() {
        System.out.println("In this program you can create a simulation of the real world, where predators eat " +
                "herbivores and herbivores eat grass.\nThe simulation can be started with default settings by " +
                "pressing 1 + enter, or you can set all simulation parameters manually by pressing 2 + enter");
    }

    @Override
    public void messageControls() {
        System.out.println("Press key: 1 - for pause the simulation, 2 - for resume the infinite simulation, " +
                "Enter - all creatures make 1 turn, Enter + Space - for end the simulation.\n");
    }

    @Override
    public void messageChangeConfig() {
        System.out.println("Please, enter the world parameters in integers.");
    }
}
