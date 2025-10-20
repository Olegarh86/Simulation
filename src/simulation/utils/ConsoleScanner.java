package simulation.utils;

import java.util.Scanner;

public class ConsoleScanner {
    Scanner scanner = new Scanner(System.in);

    public String readString() {
        return scanner.nextLine();
    }
}
