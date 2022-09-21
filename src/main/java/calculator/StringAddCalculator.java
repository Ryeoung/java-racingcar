package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    private static final String CUSTOM_DELIMITER_REGEX = "//(.)\n(.*)";
    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";
    private static final int CUSTOM_DELIMITER_POSITION = 1;
    private static final int NUMBERS_POSITION = 2;


    public static int splitAndSum(String input) {
        if (isNullOrEmpty(input)) {
            return 0;
        }

        String[] tokens = splitToken(input);

        return sum(tokens);
    }

    private static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    private static String[] splitToken(String input) {
        Matcher matcher = Pattern.compile(CUSTOM_DELIMITER_REGEX).matcher(input);
        if (matcher.find()) {
            String customDelimiter = matcher.group(CUSTOM_DELIMITER_POSITION);
            return matcher.group(NUMBERS_POSITION).split(customDelimiter);
        }

        return input.split(DEFAULT_DELIMITER_REGEX);
    }

    private static int sum(String[] tokens) {
        int result = 0;

        for (String token : tokens) {
            result += new PositiveNumber(Integer.parseInt(token)).getNumber();
        }

        return result;
    }
}