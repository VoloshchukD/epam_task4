package by.epamtc.service;

import by.epamtc.exception.NoSuchTextException;
import by.epamtc.exception.TextHandlerIndexOutOfBoundsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionHandler {

    public static final String WORDS_SEPARATOR = " ";

    public static final String CONSONANT_LETTERS = "^[бвгджзйклмнпрстфхцчшщ]";

    public static final String DIVIDE_TEXT_REGULAR_EXPRESSION = "(\\w+)([.,:;!?-]?)(\\s?)";

    public static final String DIVIDE_RUSSIAN_TEXT_REGULAR_EXPRESSION = "([а-яА-Я]+)([.,:;!?-]?)(\\s?)";

    public static final String FIND_CONCRETE_LENGTH_STRING_REGULAR_EXPRESSION_PART1 = "(\\b\\w{";

    public static final String FIND_CONCRETE_LENGTH_STRING_REGULAR_EXPRESSION_PART2 = "})([.,]?)(\\s+)";

    public static String replaceEachWordLetter(String text, char symbol, int letterNumber) throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (letterNumber < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + letterNumber);
        }
        Matcher matcher = createMatcher(text, DIVIDE_TEXT_REGULAR_EXPRESSION, false);

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

    public static String correctWrongLetter(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        Matcher matcher = createMatcher(text, DIVIDE_TEXT_REGULAR_EXPRESSION, false);

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

    public static String replaceWordsWithSubstring(String text, String substring, int wordLength)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (wordLength < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + wordLength);
        }
        Matcher matcher = createMatcher(text, DIVIDE_TEXT_REGULAR_EXPRESSION, false);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            if (matcher.group(1).length() == wordLength) {
                result.append(substring);
                result.append(matcher.group(2));
                result.append(matcher.group(3));
            } else {
                result.append(matcher.group());
            }
        }

        return result.toString();
    }

    public static String removeAllCharacters(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        Matcher matcher = createMatcher(text, DIVIDE_TEXT_REGULAR_EXPRESSION, false);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group(1));
            result.append(WORDS_SEPARATOR);
        }

        return result.toString();
    }

    public static String removeWordsStartingWithConsonant(String text, int wordLength)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (wordLength < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + wordLength);
        }
        Matcher matcher = createMatcher(text, DIVIDE_RUSSIAN_TEXT_REGULAR_EXPRESSION, false);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            Matcher wordMatcher = createMatcher(matcher.group(1), CONSONANT_LETTERS, true);

            if (matcher.group(1).length() != wordLength || !wordMatcher.find()) {
                result.append(matcher.group(1));
            }

            result.append(matcher.group(2));
            result.append(matcher.group(3));
        }

        return result.toString();
    }

    private static Matcher createMatcher(String text, String regularExpression, boolean caseInsensitive) {
        Pattern pattern;
        if (caseInsensitive) {
            pattern = Pattern.compile(regularExpression, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        } else {
            pattern = Pattern.compile(regularExpression);
        }
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }

}
