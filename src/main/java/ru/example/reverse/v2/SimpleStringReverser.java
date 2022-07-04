package main.java.ru.example.reverse.v2;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public final class SimpleStringReverser {
    private final static Scanner scanner = new Scanner(System.in);
    private static final String EXIT_SYMBOL = "n";

    public static void main(String[] args) {
        printWelcomeMsg();

        String in = "";
        while (scanner.hasNextLine() && !in.equalsIgnoreCase(EXIT_SYMBOL)) {

            in = scanner.nextLine().trim();
            onCallExit(in);
            final String reversed = reverse(in);

            if (reversed.length() > 0) {
                final BigDecimal time1 = measureEachTime(in, 1_000);
                final BigDecimal time2 = measureEachTime(in, 10_000);
                final BigDecimal time3 = measureEachTime(in, 100_000);

                System.out.printf(
                        "%nEntered string: %s%nReversed string: %s%nMeasured time: %s %s %s %n%n",
                        in,
                        reversed,
                        time1.toPlainString(),
                        time2.toPlainString(),
                        time3.toPlainString()
                );
            }

            printWelcomeMsg();
        }
    }

    /**
     * A method that expands a string in reverse order
     *
     * @param in - entered string
     * @return String - reversed string
     */
    private static String reverse(String in) {
        if (in.length() <= 1) {
            return in;
        }
        return new StringBuilder(in).reverse().toString();
    }

    private static BigDecimal measureEachTime(String in, Integer iterations) {
        final BigDecimal start = new BigDecimal(System.nanoTime());

        for (int i = 0; i < iterations; i++) {
            reverse(in);
        }

        BigDecimal nanos = new BigDecimal(System.nanoTime()).subtract(start);
        return nanos.divide(new BigDecimal(1_000_000), MathContext.UNLIMITED);
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