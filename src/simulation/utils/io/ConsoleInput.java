package simulation.utils.io;

import java.util.Scanner;

public class ConsoleInput implements Input{
    private final Scanner scanner;

    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readInput() {
        return scanner.nextLine();
    }
}
