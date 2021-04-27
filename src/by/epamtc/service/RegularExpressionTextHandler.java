package by.epamtc.service;

import by.epamtc.exception.NoSuchTextException;
import by.epamtc.exception.TextHandlerIndexOutOfBoundsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionTextHandler {

    public static final String WORDS_SEPARATOR = " ";

    public static final char WRONG_LETTER = 'A';

    public static final char WRONG_LETTER_PREFIX = 'P';

    public static final char CORRECT_LETTER = 'O';

    public static final String FIND_MISTAKE_REGULAR_EXPRESSION = "(" + WRONG_LETTER_PREFIX + ")(" + WRONG_LETTER + ")";

    public static final String STARTS_WITH_CONSONANT_LETTER_REGULAR_EXPRESSION = "^[bcdfghjklmnpqrstvwxyz]";

    public static final String DIVIDE_TEXT_ON_WORDS_REGULAR_EXPRESSION = "(\\w+)([.,:;!?-]*\\s?)";

    public static String replaceEachWordLetter(String text, char symbol, int letterNumber) throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (letterNumber < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + letterNumber);
        }
        Matcher wordMatcher = createMatcher(text, DIVIDE_TEXT_ON_WORDS_REGULAR_EXPRESSION, false);

        StringBuilder result = new StringBuilder();
        while (wordMatcher.find()) {
            StringBuilder currentWord = new StringBuilder(wordMatcher.group(1));
            if (letterNumber - 1 < currentWord.length()) {
                currentWord.setCharAt(letterNumber - 1, symbol);
            }
            currentWord.append(wordMatcher.group(2));
            result.append(currentWord);
        }

        return result.toString();
    }

    public static String correctWrongLetter(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        Matcher wordMatcher = createMatcher(text, DIVIDE_TEXT_ON_WORDS_REGULAR_EXPRESSION, false);

        StringBuilder resultText = new StringBuilder();
        while (wordMatcher.find()) {
            StringBuilder currentWord = new StringBuilder(wordMatcher.group(1));
            Matcher mistakeMatcher = createMatcher(currentWord.toString(), FIND_MISTAKE_REGULAR_EXPRESSION, false);

            while (mistakeMatcher.find()) {
                currentWord.setCharAt(mistakeMatcher.start(2), CORRECT_LETTER);
            }

            currentWord.append(wordMatcher.group(2));
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
        Matcher wordMatcher = createMatcher(text, DIVIDE_TEXT_ON_WORDS_REGULAR_EXPRESSION, false);

        StringBuilder resultText = new StringBuilder();
        while (wordMatcher.find()) {
            if (wordMatcher.group(1).length() == wordLength) {
                resultText.append(substring);
                resultText.append(wordMatcher.group(2));
            } else {
                resultText.append(wordMatcher.group());
            }
        }

        return resultText.toString();
    }

    public static String removeAllCharacters(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        Matcher wordMatcher = createMatcher(text, DIVIDE_TEXT_ON_WORDS_REGULAR_EXPRESSION, false);

        StringBuilder resultText = new StringBuilder();
        while (wordMatcher.find()) {
            resultText.append(wordMatcher.group(1));
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
        Matcher wordMatcher = createMatcher(text, DIVIDE_TEXT_ON_WORDS_REGULAR_EXPRESSION, false);

        StringBuilder resultText = new StringBuilder();
        while (wordMatcher.find()) {
            Matcher currentWordMatcher = createMatcher(wordMatcher.group(1), STARTS_WITH_CONSONANT_LETTER_REGULAR_EXPRESSION, true);

            if (wordMatcher.group(1).length() != wordLength || !currentWordMatcher.find()) {
                resultText.append(wordMatcher.group(1));
            }

            resultText.append(wordMatcher.group(2));
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
