package by.epamtc.run;

import by.epamtc.exception.WrongFileNameException;
import by.epamtc.service.CharTextHandler;
import by.epamtc.service.RegularExpressionHandler;
import by.epamtc.service.StringTextHandler;
import by.epamtc.service.TextReader;

import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException, WrongFileNameException {
        String text = TextReader.readTextFromFile("data.txt");
        System.out.println(StringTextHandler.replaceEachWordLetter(text, "P", 3));
        System.out.println(CharTextHandler.replaceEachWordLetter(text, 'P', 3));
        System.out.println(RegularExpressionHandler.replaceEachWordLetter(text, 'P', 3));

        text = "hfPA, sfhjP, sflkjPO";
        System.out.println(StringTextHandler.correctWrongLetter(text));
        System.out.println(CharTextHandler.correctWrongLetter(text));
        System.out.println(RegularExpressionHandler.correctWrongLetter(text));

        text = "hfP, sfhd, sfg, dsdaf.";
        System.out.println(StringTextHandler.replaceWordsWithSubstring(text, "AAAAA", 3));
        System.out.println(CharTextHandler.replaceWordsWithSubstring(text, "AAAAA", 3));
        System.out.println(RegularExpressionHandler.replaceWordsWithSubstring(text, "AAAAA", 3));

        text = "hello, its, me.  ";
        System.out.println(StringTextHandler.removeAllCharacters(text));
        System.out.println(CharTextHandler.removeAllCharacters(text));
        System.out.println(RegularExpressionHandler.removeAllCharacters(text));

        text = "Фыва, Фыа, Про, лкец, зло.";
        System.out.println(StringTextHandler.removeWordsStartingWithConsonant(text, 3));
        System.out.println(CharTextHandler.removeWordsStartingWithConsonant(text, 3));
        System.out.println(RegularExpressionHandler.removeWordsStartingWithConsonant(text, 3));
    }
}