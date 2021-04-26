package by.epamtc.service;

import by.epamtc.exception.NoSuchTextException;
import by.epamtc.exception.TextHandlerIndexOutOfBoundsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionTextHandler {

    public static final String WORDS_SEPARATOR = " ";

    public static final String CONSONANT_LETTERS = "^[bcdfghjklmnpqrstvwxyz]";

    public static final String DIVIDE_TEXT_REGULAR_EXPRESSION = "(\\w+)([.,:;!?-]*\\s?)";

    public static final char WRONG_LETTER = 'A';

    public static final char WRONG_LETTER_PREFIX = 'P';

    public static final char CORRECT_LETTER = 'O';

    public static final String FIND_MISTAKE_REGULAR_EXPRESSION = "(" + WRONG_LETTER_PREFIX + ")(" + WRONG_LETTER + ")";

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
            result.append(currentWord);
        }

        return result.toString();
    }

    public static String correctWrongLetter(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        Matcher matcher = createMatcher(text, DIVIDE_TEXT_REGULAR_EXPRESSION, false);

        StringBuilder resultText = new StringBuilder();
        while (matcher.find()) {
            StringBuilder currentWord = new StringBuilder(matcher.group(1));
            Matcher mistakeMatcher = createMatcher(currentWord.toString(), FIND_MISTAKE_REGULAR_EXPRESSION, false);

            while (mistakeMatcher.find()) {
                currentWord.setCharAt(mistakeMatcher.start(2), CORRECT_LETTER);
            }

            currentWord.append(matcher.group(2));
            resultText.append(currentWord);
        }

        return resultText.toString();
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

        StringBuilder resultText = new StringBuilder();
        while (matcher.find()) {
            if (matcher.group(1).length() == wordLength) {
                resultText.append(substring);
                resultText.append(matcher.group(2));
            } else {
                resultText.append(matcher.group());
            }
        }

        return resultText.toString();
    }

    public static String removeAllCharacters(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        Matcher matcher = createMatcher(text, DIVIDE_TEXT_REGULAR_EXPRESSION, false);

        StringBuilder resultText = new StringBuilder();
        while (matcher.find()) {
            resultText.append(matcher.group(1));
            resultText.append(WORDS_SEPARATOR);
        }

        return resultText.toString();
    }

    public static String removeWordsStartingWithConsonant(String text, int wordLength)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (wordLength < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + wordLength);
        }
        Matcher matcher = createMatcher(text, DIVIDE_TEXT_REGULAR_EXPRESSION, false);

        StringBuilder resultText = new StringBuilder();
        while (matcher.find()) {
            Matcher wordMatcher = createMatcher(matcher.group(1), CONSONANT_LETTERS, true);

            if (matcher.group(1).length() != wordLength || !wordMatcher.find()) {
                resultText.append(matcher.group(1));
            }

            resultText.append(matcher.group(2));
        }

        return resultText.toString();
    }

    private static Matcher createMatcher(String text, String regularExpression, boolean caseInsensitive) {
        Pattern pattern;
        if (caseInsensitive) {
            pattern = Pattern.compile(regularExpression, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(regularExpression);
        }
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }

}
