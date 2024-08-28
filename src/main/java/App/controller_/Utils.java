package App.controller_;

import java.util.Scanner;

public abstract class Utils {

    private static Scanner reader = new Scanner(System.in);

    public static Scanner getReader() {
        return reader;
    }
}
