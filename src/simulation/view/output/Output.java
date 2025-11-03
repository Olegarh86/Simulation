package simulation.view.output;

public interface Output {
    void printGreetings();
    void printMessageControls();
    void printMessage(String massage);
    void printCount(int countOfMoves);
    void printMessageChangeConfig();
    void printDefaultConfigWithDelay();
}
