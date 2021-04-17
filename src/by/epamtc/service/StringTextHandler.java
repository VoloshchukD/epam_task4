package by.epamtc.service;

public class StringTextHandler {

    public static final String WORDS_SEPARATOR = " ";

    public static final String[] CONSONANT_LETTERS = {"б", "в", "г", "д", "ж", "з", "й", "к", "л", "м", "н",
            "п", "р", "с", "т", "ф", "х", "ц", "ч", "ш", "щ"};

    public static final String[] PUNCTUATION_MARKS = {".", ",", ":", ";", "!", "?", "-"};

    public static String replaceEachWordLetter(String text, String symbol, int letterNumber) {
        String[] words = text.trim().split(WORDS_SEPARATOR);

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

    public static String correctWrongLetter(String text) {
        String[] words = text.trim().split(WORDS_SEPARATOR);

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int currentWordLength = word.length();
            if (isPunctuationMark(word.substring(currentWordLength - 1, currentWordLength))) {
                currentWordLength--;
            }
            if (word.charAt(currentWordLength - 1) == 'A' && word.charAt(currentWordLength - 2) == 'P') {
                word = word.replace('A', 'O');
                words[i] = word;
            }
        }

        return wordsToString(words);
    }

    public static String replaceWordsWithSubstring(String text, String substring, int wordLength) {
        String[] words = text.trim().split(WORDS_SEPARATOR);

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

    public static String removeAllCharacters(String text) {
        String[] words = text.trim().split(WORDS_SEPARATOR);

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            int currentWordLength = word.length();
            if (isPunctuationMark(word.substring(currentWordLength - 1, currentWordLength))) {
                word.deleteCharAt(currentWordLength - 1);
                words[i] = word.toString();
            }
        }

        return wordsToString(words);
    }

    public static String removeWordsStartingWithConsonant(String text, int wordLength) {
        String[] words = text.trim().split(WORDS_SEPARATOR);

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            int currentWordLength = word.length();
            String punctuationMark = "";
            if (isPunctuationMark(word.substring(currentWordLength - 1, currentWordLength))) {
                punctuationMark = String.valueOf(word.charAt(currentWordLength - 1));
                currentWordLength--;
            }
            if (currentWordLength == wordLength && isConsonantLetter(word.substring(0,1))) {
                words[i] = punctuationMark;
            }
        }

        return wordsToString(words);
    }

    public static boolean isConsonantLetter(String letter) {
        boolean result = false;
        for (String consonantLetter : CONSONANT_LETTERS) {
            if (consonantLetter.equalsIgnoreCase(letter)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isPunctuationMark(String symbol) {
        boolean result = false;
        for (String punctuationMark : PUNCTUATION_MARKS) {
            if (punctuationMark.equals(symbol)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static String wordsToString(String[] words) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                stringBuilder.append(word);
                stringBuilder.append(WORDS_SEPARATOR);
            }
        }

        return stringBuilder.toString();
    }

}
