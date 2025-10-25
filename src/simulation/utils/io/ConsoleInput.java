package simulation.utils.io;

import java.util.Scanner;

public class ConsoleInput implements Input{
    Scanner scanner = new Scanner(System.in);

    @Override
    public String readString() {
        return scanner.nextLine();
    }
}
