package simulation.view.output;

public interface Output {
    void greeting();
    void controls();
    void message(String massage);
    void count(int countOfMoves);
    void changeConfig();
    void defaultConfig();
}
