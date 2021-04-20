package by.epamtc.service;

public class CharTextHandler {

    public static final char WORDS_SEPARATOR = ' ';

    public static final char[] CONSONANT_LETTERS = {'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н',
            'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ'};

    public static final char[] PUNCTUATION_MARKS = {'.', ',', ':', ';', '!', '?', '-'};

    public static String replaceEachWordLetter(String text, char symbol, int letterNumber) {
        char[] chars = text.toCharArray();

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!isPunctuationMark(chars[i]) && chars[i] != WORDS_SEPARATOR) {
                currentWordLength++;
            }
            if (chars[i] == WORDS_SEPARATOR || i == chars.length - 1) {
                if (currentWordLength >= letterNumber) {
                    chars[previousSeparatorIndex + letterNumber] = symbol;
                }
                previousSeparatorIndex = i;
                currentWordLength = 0;
            }
        }

        return new String(chars);
    }

    public static String correctWrongLetter(String text) {
        char[] chars = text.toCharArray();

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!isPunctuationMark(chars[i]) && chars[i] != WORDS_SEPARATOR) {
                currentWordLength++;
            }
            if (chars[i] == WORDS_SEPARATOR || i == chars.length - 1) {
                if (chars[previousSeparatorIndex + currentWordLength] == 'A'
                        && chars[previousSeparatorIndex + currentWordLength - 1] == 'P') {
                    chars[previousSeparatorIndex + currentWordLength] = 'O';
                }
                previousSeparatorIndex = i;
                currentWordLength = 0;
            }
        }

        return new String(chars);
    }

    public static String replaceWordsWithSubstring(String text, String substring, int wordLength) {
        char[] chars = text.toCharArray();

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!isPunctuationMark(chars[i]) && chars[i] != WORDS_SEPARATOR) {
                currentWordLength++;
            }
            if (chars[i] == WORDS_SEPARATOR || i == chars.length - 1) {
                if (currentWordLength == wordLength) {
                    char[] newChar = new char[chars.length + substring.length() - currentWordLength];

                    for (int j = 0; j <= previousSeparatorIndex; j++) {
                        newChar[j] = chars[j];
                    }

                    char[] substringAsChar = substring.toCharArray();
                    int k = previousSeparatorIndex + 1;
                    for (int j = 0; j < substringAsChar.length; j++) {
                        newChar[k] = substringAsChar[j];
                        k++;
                    }

                    for (int j = previousSeparatorIndex + currentWordLength + 1; j < chars.length; j++) {
                        newChar[k] = chars[j];
                        k++;
                    }

                    chars = newChar;
                }
                previousSeparatorIndex = i;
                currentWordLength = 0;
            }
        }

        return new String(chars);
    }

    public static String removeAllCharacters(String text) {
        char[] chars = text.toCharArray();

        int removedCharsCounter = -1;
        for (int i = 0; i < chars.length; i++) {
            if (isPunctuationMark(chars[i])) {
                chars[i] = '\u0000';
                removedCharsCounter++;
            }
        }

        return charsToString(chars, removedCharsCounter);
    }

    public static String removeWordsStartingWithConsonant(String text, int wordLength) {
        char[] chars = text.toCharArray();

        int previousSeparatorIndex = -1;
        int currentWordLength = 0;
        int removedCharsCounter = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!isPunctuationMark(chars[i]) && chars[i] != WORDS_SEPARATOR) {
                currentWordLength++;
            }

            if (chars[i] == WORDS_SEPARATOR || i == chars.length - 1) {
                if (currentWordLength == wordLength && isConsonantLetter(chars[previousSeparatorIndex + 1])) {
                    for (int j = previousSeparatorIndex + 1; j < previousSeparatorIndex + currentWordLength + 1; j++) {
                        chars[j] = '\u0000';
                        removedCharsCounter++;
                    }
                }
                previousSeparatorIndex = i;
                currentWordLength = 0;
            }
        }

        return charsToString(chars, removedCharsCounter);
    }

    public static boolean isPunctuationMark(char character) {
        boolean result = false;
        for (char punctuationMark : PUNCTUATION_MARKS) {
            if (punctuationMark == character) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isConsonantLetter(char letter) {
        boolean result = false;
        for (char consonantLetter : CONSONANT_LETTERS) {
            if (consonantLetter == letter) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static String charsToString(char[] chars, int removedCharsNumber) {
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

}
