package ru.example.reverse.v1;

import java.math.BigDecimal;
import java.util.ArrayList;

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