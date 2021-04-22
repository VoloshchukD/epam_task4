package by.epamtc.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionHandler {

    public static final String WORDS_SEPARATOR = " ";

    public static final String CONSONANT_LETTERS = "^[бвгджзйклмнпрстфхцчшщ]";

    public static final String PUNCTUATION_MARKS = ".,:;!?-";

    public static final String DIVIDE_TEXT_REGULAR_EXPRESSION = "(\\w+)([.,:;!?-]?)(\\s?)";

    public static final String DIVIDE_RUSSIAN_TEXT_REGULAR_EXPRESSION = "([а-яА-Я]+)([.,:;!?-]?)(\\s?)";

    public static final String FIND_CONCRETE_LENGTH_STRING_REGULAR_EXPRESSION_PART1 = "(\\b\\w{";

    public static final String FIND_CONCRETE_LENGTH_STRING_REGULAR_EXPRESSION_PART2 = "})([.,]?)(\\s+)";

    public static String replaceEachWordLetter(String text, char symbol, int letterNumber) {
        Pattern pattern = Pattern.compile(DIVIDE_TEXT_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(text);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            StringBuilder currentWord = new StringBuilder(matcher.group(1));
            if (letterNumber - 1 < currentWord.length()) {
                currentWord.setCharAt(letterNumber - 1, symbol);
            }
            currentWord.append(matcher.group(2));
            currentWord.append(matcher.group(3));
            result.append(currentWord);
        }

        return result.toString();
    }

    public static String correctWrongLetter(String text) {
        Pattern pattern = Pattern.compile(DIVIDE_TEXT_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(text);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            StringBuilder currentWord = new StringBuilder(matcher.group(1));
            if (currentWord.charAt(currentWord.length() - 1) == 'A'
                    && currentWord.charAt(currentWord.length() - 2) == 'P') {
                currentWord.setCharAt(currentWord.length() - 1, 'O');
            }
            currentWord.append(matcher.group(2));
            currentWord.append(matcher.group(3));
            result.append(currentWord);
        }

        return result.toString();
    }

    public static String replaceWordsWithSubstring(String text, String substring, int wordLength) {
        Pattern pattern = Pattern.compile(DIVIDE_TEXT_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(text);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            StringBuilder currentWord = new StringBuilder();
            if (matcher.group(1).length() == wordLength) {
                currentWord.append(substring);
                currentWord.append(matcher.group(2));
                currentWord.append(matcher.group(3));
                result.append(currentWord);
            } else {
                result.append(matcher.group());
            }
        }

        return result.toString();
    }

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

    public static String removeWordsStartingWithConsonant(String text, int wordLength) {
        Pattern pattern = Pattern.compile(DIVIDE_RUSSIAN_TEXT_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(text);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            StringBuilder currentWord = new StringBuilder();
            Pattern wordPattern = Pattern.compile(CONSONANT_LETTERS, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher wordMatcher = wordPattern.matcher(matcher.group(1));

            if (matcher.group(1).length() != wordLength || !wordMatcher.find()) {
                currentWord.append(matcher.group(1));
            }

            currentWord.append(matcher.group(2));
            currentWord.append(matcher.group(3));
            result.append(currentWord);
        }

        return result.toString();
    }

}
