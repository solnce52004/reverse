package ru.example.reverse.v1;

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
                getReversedString(in)
        );
    }

    private String getReversedString(String in) {
        final char[] charArr = in.toCharArray();
        final int lastIndex = charArr.length - 1;

        for (int i = 0; i < charArr.length / 2; i++) {
            char tmp = charArr[i];
            charArr[i] = charArr[lastIndex - i];
            charArr[lastIndex - i] = tmp;
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArr.length; i++) {
            sb.append(charArr[i]);
        }

        return sb.toString();
    }

    private String getReversedStringVsSB(String in) {
        return new StringBuilder(in).reverse().toString();
    }
}
