package by.epamtc.service;

import by.epamtc.exception.NoSuchTextException;
import by.epamtc.exception.TextHandlerIndexOutOfBoundsException;

//Methods do not use charAt() method calls and explicit work with the type char
public class StringTextHandler {

    public static final String WORDS_SEPARATOR = " ";

    public static final String[] CONSONANT_LETTERS = {"б", "в", "г", "д", "ж", "з", "й", "к", "л", "м", "н",
            "п", "р", "с", "т", "ф", "х", "ц", "ч", "ш", "щ"};

    public static final String[] PUNCTUATION_MARKS = {".", ",", ":", ";", "!", "?", "-"};

    public static final String WRONG_LETTER = "A";

    public static final String WRONG_LETTER_PREFIX = "P";

    public static final String CORRECT_LETTER = "O";

    public static String replaceEachWordLetter(String text, String symbol, int letterNumber)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (letterNumber < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + letterNumber);
        }
        String[] words = textToStringArray(text);

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            int currentWordLength = word.length();
            if (isPunctuationMark(word.substring(currentWordLength - 1, currentWordLength))) {
                currentWordLength--;
            }
            if (letterNumber <= currentWordLength) {
                int replaceIndex = letterNumber - 1;
                word.replace(replaceIndex, replaceIndex + 1, symbol);
                words[i] = word.toString();
            }
        }

        return wordsToString(words);
    }

    public static String correctWrongLetter(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        String[] words = textToStringArray(text);

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int currentWordLength = word.length();
            if (isPunctuationMark(word.substring(currentWordLength - 1, currentWordLength))) {
                currentWordLength--;
            }
            for (int j = 1; j < currentWordLength; j++) {
                if (word.substring(j, j + 1).equals(WRONG_LETTER)
                        && word.substring(j - 1, j).equals(WRONG_LETTER_PREFIX)) {
                    word = word.replace(WRONG_LETTER, CORRECT_LETTER);
                    words[i] = word;
                }
            }
        }

        return wordsToString(words);
    }

    public static String replaceWordsWithSubstring(String text, String substring, int wordLength)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (wordLength < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + wordLength);
        }
        String[] words = textToStringArray(text);

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            int currentWordLength = word.length();
            String punctuationMark = "";
            if (isPunctuationMark(word.substring(currentWordLength - 1, currentWordLength))) {
                punctuationMark = word.substring(currentWordLength - 1);
                currentWordLength--;
            }
            if (currentWordLength == wordLength) {
                words[i] = substring + punctuationMark;
            }
        }

        return wordsToString(words);
    }

    public static String removeAllCharacters(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        String[] words = textToStringArray(text);

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            int currentWordLength = word.length();
            if (isPunctuationMark(word.substring(currentWordLength - 1, currentWordLength))) {
                word.delete(currentWordLength - 1, currentWordLength);
                words[i] = word.toString();
            }
        }

        return wordsToString(words);
    }

    public static String removeWordsStartingWithConsonant(String text, int wordLength)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (wordLength < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + wordLength);
        }
        String[] words = textToStringArray(text);

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            int currentWordLength = word.length();
            String punctuationMark = "";
            if (isPunctuationMark(word.substring(currentWordLength - 1, currentWordLength))) {
                punctuationMark = word.substring(currentWordLength - 1, currentWordLength);
                currentWordLength--;
            }
            if (currentWordLength == wordLength && isConsonantLetter(word.substring(0, 1))) {
                words[i] = punctuationMark;
            }
        }

        return wordsToString(words);
    }

    private static boolean isConsonantLetter(String letter) {
        boolean result = false;
        for (String consonantLetter : CONSONANT_LETTERS) {
            if (consonantLetter.equalsIgnoreCase(letter)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static boolean isPunctuationMark(String symbol) {
        boolean result = false;
        for (String punctuationMark : PUNCTUATION_MARKS) {
            if (punctuationMark.equals(symbol)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static String wordsToString(String[] words) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                stringBuilder.append(word);
                stringBuilder.append(WORDS_SEPARATOR);
            }
        }

        return stringBuilder.toString();
    }

    private static String[] textToStringArray(String text) {
        String formattedText = removeRedundantSeparators(text);
        int wordsInTextCounter = 0;
        for (int i = 0; i < formattedText.length(); i++) {
            if (formattedText.substring(i, i + 1).equals(WORDS_SEPARATOR) || i == formattedText.length() - 1) {
                wordsInTextCounter++;
            }
        }
        String[] words = new String[wordsInTextCounter];

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        int wordsArrayAddingIndex = 0;
        for (int i = 0; i < formattedText.length(); i++) {
            boolean isSeparator = formattedText.substring(i, i + 1).equals(WORDS_SEPARATOR);
            if (!isSeparator) {
                currentWordLength++;
            }
            if (isSeparator || i == formattedText.length() - 1) {
                String currentWord = formattedText.substring(previousSeparatorIndex + 1,
                        previousSeparatorIndex + currentWordLength + 1);
                words[wordsArrayAddingIndex] = currentWord;
                wordsArrayAddingIndex++;

                currentWordLength = 0;
                previousSeparatorIndex = i;
            }
        }

        return words;
    }

    private static String removeRedundantSeparators(String text) {
        String trimmedText = text.trim();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < trimmedText.length(); i++) {
            if (i != 0 || i != trimmedText.length() - 1) {
                if (trimmedText.substring(i, i + 1).equals(WORDS_SEPARATOR)
                        && trimmedText.substring(i - 1, i).equals(WORDS_SEPARATOR)) {
                    continue;
                }
            }
            result.append(trimmedText.substring(i, i + 1));
        }

        return result.toString();
    }

}
