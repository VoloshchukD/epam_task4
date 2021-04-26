package by.epamtc.service;

import by.epamtc.exception.NoSuchTextException;
import by.epamtc.exception.TextHandlerIndexOutOfBoundsException;

public class CharTextHandler {

    public static final char WORDS_SEPARATOR = ' ';

    public static final char[] CONSONANT_LETTERS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k',
            'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};

    public static final char[] PUNCTUATION_MARKS = {'.', ',', ':', ';', '!', '?', '-'};

    public static final char WRONG_LETTER = 'A';

    public static final char WRONG_LETTER_PREFIX = 'P';

    public static final char CORRECT_LETTER = 'O';

    public static final int ENCODING_UPPERCASE_AND_LOWERCASE_POSITION_DIFFERENCE = 32;

    public static String replaceEachWordLetter(String text, char symbol, int letterNumber)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (letterNumber < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + letterNumber);
        }

        char[] textChars = removeRedundantSeparators(text.toCharArray());

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        for (int i = 0; i < textChars.length; i++) {
            if (!isPunctuationMark(textChars[i]) && textChars[i] != WORDS_SEPARATOR) {
                currentWordLength++;
            }
            if (textChars[i] == WORDS_SEPARATOR || i == textChars.length - 1) {
                if (currentWordLength >= letterNumber) {
                    textChars[previousSeparatorIndex + letterNumber] = symbol;
                }
                previousSeparatorIndex = i;
                currentWordLength = 0;
            }
        }

        return new String(textChars);
    }

    public static String correctWrongLetter(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        char[] textChars = removeRedundantSeparators(text.toCharArray());

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        for (int i = 0; i < textChars.length; i++) {
            if (!isPunctuationMark(textChars[i]) && textChars[i] != WORDS_SEPARATOR) {
                currentWordLength++;
            }
            if (textChars[i] == WORDS_SEPARATOR || i == textChars.length - 1) {
                for (int j = previousSeparatorIndex + 2; j <= previousSeparatorIndex + currentWordLength; j++) {
                    if (textChars[j] == WRONG_LETTER && textChars[j - 1] == WRONG_LETTER_PREFIX) {
                        textChars[j] = CORRECT_LETTER;
                    }
                }
                previousSeparatorIndex = i;
                currentWordLength = 0;
            }
        }

        return new String(textChars);
    }

    public static String replaceWordsWithSubstring(String text, String substring, int wordLength)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (wordLength < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + wordLength);
        }
        char[] textChars = removeRedundantSeparators(text.toCharArray());

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        for (int i = 0; i < textChars.length; i++) {
            if (!isPunctuationMark(textChars[i]) && textChars[i] != WORDS_SEPARATOR) {
                currentWordLength++;
            }
            if (textChars[i] == WORDS_SEPARATOR || i == textChars.length - 1) {
                if (currentWordLength == wordLength) {
                    char[] newChars = new char[textChars.length + substring.length() - currentWordLength];

                    for (int j = 0; j <= previousSeparatorIndex; j++) {
                        newChars[j] = textChars[j];
                    }

                    char[] substringChars = substring.toCharArray();
                    int k = previousSeparatorIndex + 1;
                    for (int j = 0; j < substringChars.length; j++) {
                        newChars[k] = substringChars[j];
                        k++;
                    }

                    for (int j = previousSeparatorIndex + currentWordLength + 1; j < textChars.length; j++) {
                        newChars[k] = textChars[j];
                        k++;
                    }

                    textChars = newChars;
                }
                previousSeparatorIndex = i;
                currentWordLength = 0;
            }
        }

        return new String(textChars);
    }

    public static String removeAllCharacters(String text) throws NoSuchTextException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        char[] textChars = removeRedundantSeparators(text.toCharArray());

        int removedCharsCounter = -1;
        for (int i = 0; i < textChars.length; i++) {
            if (isPunctuationMark(textChars[i])) {
                textChars[i] = '\u0000';
                removedCharsCounter++;
            }
        }

        return charsToString(textChars, removedCharsCounter);
    }

    public static String removeWordsStartingWithConsonant(String text, int wordLength)
            throws NoSuchTextException, TextHandlerIndexOutOfBoundsException {
        if (text == null) {
            throw new NoSuchTextException("No text present");
        }
        if (wordLength < 0) {
            throw new TextHandlerIndexOutOfBoundsException("Index out of bounds " + wordLength);
        }
        char[] textChars = removeRedundantSeparators(text.toCharArray());

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        int removedCharsCounter = 0;
        for (int i = 0; i < textChars.length; i++) {
            if (!isPunctuationMark(textChars[i]) && textChars[i] != WORDS_SEPARATOR) {
                currentWordLength++;
            }

            if (textChars[i] == WORDS_SEPARATOR || i == textChars.length - 1) {
                if (currentWordLength == wordLength && isConsonantLetter(textChars[previousSeparatorIndex + 1])) {
                    for (int j = previousSeparatorIndex + 1; j < previousSeparatorIndex + currentWordLength + 1; j++) {
                        textChars[j] = '\u0000';
                        removedCharsCounter++;
                    }
                }
                previousSeparatorIndex = i;
                currentWordLength = 0;
            }
        }

        return charsToString(textChars, removedCharsCounter);
    }

    private static boolean isPunctuationMark(char character) {
        boolean result = false;
        for (char punctuationMark : PUNCTUATION_MARKS) {
            if (punctuationMark == character) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static boolean isConsonantLetter(char letter) {
        boolean result = false;
        for (char consonantLetter : CONSONANT_LETTERS) {
            if (consonantLetter == letter || ((char) (consonantLetter -
                    ENCODING_UPPERCASE_AND_LOWERCASE_POSITION_DIFFERENCE) == letter)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static String charsToString(char[] chars, int removedCharsNumber) {
        char[] resultChars = new char[chars.length - removedCharsNumber];

        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '\u0000') {
                resultChars[j] = chars[i];
                j++;
            }
        }

        return new String(resultChars);
    }

    private static char[] removeRedundantSeparators(char[] textChars) {
        int removedCharsNumber = 0;
        for (int i = 0; i < textChars.length; i++) {
            if (i == 0) {
                for (int j = i; j < textChars.length; j++) {
                    if (textChars[j] == WORDS_SEPARATOR) {
                        textChars[j] = '\u0000';
                        removedCharsNumber++;
                    } else {
                        break;
                    }
                }
            } else if (i == textChars.length - 1) {
                for (int j = i; j >= 0; j--) {
                    if (textChars[j] == WORDS_SEPARATOR) {
                        textChars[j] = '\u0000';
                        removedCharsNumber++;
                    } else {
                        break;
                    }
                }
            } else {
                if (textChars[i] == WORDS_SEPARATOR && textChars[i - 1] == WORDS_SEPARATOR) {
                    textChars[i - 1] = '\u0000';
                    removedCharsNumber++;
                }
            }
        }

        return charsToString(textChars, removedCharsNumber).toCharArray();
    }

}
