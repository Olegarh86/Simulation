package simulation.utils.io;

public interface Output {
    void printGreetings();
    void printMessageControls();
    void printMessage(String massage);
    void printCount(int countOfMoves);
    void printMessageChangeConfig();
    void printDefaultConfigWithDelay();
    void incorrectKey();
}
