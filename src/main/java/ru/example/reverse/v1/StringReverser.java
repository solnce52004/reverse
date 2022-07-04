package main.java.ru.example.reverse.v1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
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

final class Reverser {
    /**
     * A method that expands a string in reverse order
     *
     * @param resObj - result object
     * @return ResultObject
     */
    public ResultObject reverse(ResultObject resObj) {
        String in = resObj.getInString();

        if (in.length() <= 1) {
            return resObj;
        }
        return resObj.setReversedString(
                new StringBuffer(in).reverse().toString()
        );
    }
}

final class Measurer {
    private Integer iteration = 1;

    public Integer getIteration() {
        return iteration;
    }

    public void setIteration(Integer iteration) {
        this.iteration = iteration;
    }

    public ResultObject measure(ResultObject resObj) {
        final ArrayList<Integer> iterationsList = new ArrayList<>(
                Arrays.asList(1_000, 10_000, 100_000));

        for (Integer it : iterationsList) {
            setIteration(it);
            measureEachTime(resObj);
        }
        return resObj;
    }

    private void measureEachTime(ResultObject resObj) {
        final long start = System.nanoTime();

        for (int i = 0; i < getIteration(); i++) {
            new Reverser().reverse(resObj);
        }

        final BigDecimal time = new BigDecimal(System.nanoTime() - start)
                .divide(new BigDecimal(1_000_000), MathContext.UNLIMITED);

        resObj.addTime(time);
    }
}

final class ResultObject {
    private final String inString;
    private String reversedString;
    private final ArrayList<BigDecimal> times = new ArrayList<>();

    ResultObject(String inString) {
        this.inString = inString;
    }

    public void addTime(BigDecimal time) {
        times.add(time);
    }

    public String getTimesString() {
        final StringBuilder sb = new StringBuilder();
        times.forEach(t -> sb.append(t.toPlainString()).append(" "));
        return sb.toString();
    }

    public String getInString() {
        return inString;
    }

    public ResultObject setReversedString(String reversedString) {
        this.reversedString = reversedString;
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "%nEntered string: %s%nReversed string: %s%nMeasured time: %s%n%n",
                inString,
                reversedString,
                getTimesString()
        );
    }
}
