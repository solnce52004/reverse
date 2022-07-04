package ru.example.reverse.v1;

import java.util.Scanner;
import java.util.function.Function;

public final class StringReverser {
    private final static Scanner scanner = new Scanner(System.in);
    private static final String EXIT_SYMBOL = "n";

    public static void main(String[] args) {
        printWelcomeMsg();
        String in = "";
        while (scanner.hasNextLine() && !in.equalsIgnoreCase(EXIT_SYMBOL)) {
            in = scanner.nextLine().trim();
            onCallExit(in);
            final ResultObject resultObject = new ResultObject(in);

            if (resultObject.getInString().length() > 0) {
                final Reverser reverser = new Reverser();
                final Measurer measurer = new Measurer();

                final ResultObject result = ((Function<ResultObject, ResultObject>)
                        reverser::reverse)
                        .andThen(measurer::measure)
                        .apply(resultObject);

                System.out.println(result);
            }

            printWelcomeMsg();
        }
    }

    private static void printWelcomeMsg() {
        System.out.println("Enter a line (to exit enter \"n\"):");
    }

    private static void onCallExit(String in) {
        if (in.equalsIgnoreCase(EXIT_SYMBOL)) {
            System.out.println("Exit. Bye!");
            System.exit(0);
        }
    }
}
