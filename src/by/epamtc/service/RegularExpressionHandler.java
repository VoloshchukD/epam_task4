package by.epamtc.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionHandler {

    public static final String WORDS_SEPARATOR = " ";

    public static final String CONSONANT_LETTERS = "бвгджзйклмнпрстфхцчшщ";

    public static final String PUNCTUATION_MARKS = ".,:;!?-";

    public static final String DIVIDE_TEXT_REGULAR_EXPRESSION = "(\\w+)([.,:;!?-]?)(\\s?)";

    public static final String FIND_CONCRETE_LENGTH_STRING_REGULAR_EXPRESSION_PART1 = "(\\b\\w{";

    public static final String FIND_CONCRETE_LENGTH_STRING_REGULAR_EXPRESSION_PART2 = "})([.,]?)(\\s+)";

    public static String removeAllCharacters(String text) {
        Pattern pattern = Pattern.compile(DIVIDE_TEXT_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(text);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group(1));
            result.append(WORDS_SEPARATOR);
        }

        return result.toString();
    }

}
