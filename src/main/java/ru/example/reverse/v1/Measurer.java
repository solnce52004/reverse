package ru.example.reverse.v1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;

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
