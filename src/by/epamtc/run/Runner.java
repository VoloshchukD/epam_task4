package by.epamtc.run;

import by.epamtc.exception.NoSuchTextException;
import by.epamtc.exception.TextHandlerIndexOutOfBoundsException;
import by.epamtc.exception.WrongFileNameException;
import by.epamtc.service.CharTextHandler;
import by.epamtc.service.RegularExpressionTextHandler;
import by.epamtc.service.StringTextHandler;
import by.epamtc.service.TextReader;

import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException,
            WrongFileNameException, TextHandlerIndexOutOfBoundsException, NoSuchTextException {
        String text = TextReader.readTextFromFile("data.txt");
        System.out.println(StringTextHandler.replaceEachWordLetter(text, "P", 3));
        System.out.println(CharTextHandler.replaceEachWordLetter(text, 'P', 3));
        System.out.println(RegularExpressionTextHandler.replaceEachWordLetter(text, 'P', 3));

        text = "    PAhfPA,    apsfhjP,    sflkjPO   ";
        System.out.println(StringTextHandler.correctWrongLetter(text));
        System.out.println(CharTextHandler.correctWrongLetter(text));
        System.out.println(RegularExpressionTextHandler.correctWrongLetter(text));

        text = "    hfP,   sfhd,   sfg,   dsdaf.   ";
        System.out.println(StringTextHandler.replaceWordsWithSubstring(text, "AAAAA", 3));
        System.out.println(CharTextHandler.replaceWordsWithSubstring(text, "AAAAA", 3));
        System.out.println(RegularExpressionTextHandler.replaceWordsWithSubstring(text, "AAAAA", 3));

        text = "   hello, its,    me.    ";
        System.out.println(StringTextHandler.removeAllCharacters(text));
        System.out.println(CharTextHandler.removeAllCharacters(text));
        System.out.println(RegularExpressionTextHandler.removeAllCharacters(text));

        text = "   Fojd,    Hkd,    Pro,    jkds,   jkh.  ";
        System.out.println(StringTextHandler.removeWordsStartingWithConsonant(text, 3));
        System.out.println(CharTextHandler.removeWordsStartingWithConsonant(text, 3));
        System.out.println(RegularExpressionTextHandler.removeWordsStartingWithConsonant(text, 3));
    }
}