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
    public static void main(String[] args) {
        String text = null;
        try {
            text = TextReader.readTextFromFile("data.txt");
            System.out.println(StringTextHandler.replaceEachWordLetter(text, "P", 3));
            System.out.println(CharTextHandler.replaceEachWordLetter(text, 'P', 3));
            System.out.println(RegularExpressionTextHandler.replaceEachWordLetter(text, 'P', 3));
        } catch (FileNotFoundException | WrongFileNameException
                | NoSuchTextException | TextHandlerIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        text = "    PAhfPA,    apsfhjP,    sflkjPO   ";
        try {
            System.out.println(StringTextHandler.correctWrongLetter(text));
            System.out.println(CharTextHandler.correctWrongLetter(text));
            System.out.println(RegularExpressionTextHandler.correctWrongLetter(text));
        } catch (NoSuchTextException e) {
            e.printStackTrace();
        }

        text = "    hfP,   sfhd,   sfg,   dsdaf.   ";
        try {
            System.out.println(StringTextHandler.replaceWordsWithSubstring(text, "AAAAA", 3));
            System.out.println(CharTextHandler.replaceWordsWithSubstring(text, "AAAAA", 3));
            System.out.println(RegularExpressionTextHandler.replaceWordsWithSubstring(
                    text, "AAAAA", 3));
        } catch (NoSuchTextException e) {
            e.printStackTrace();
        } catch (TextHandlerIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        text = "   hello, its,    me.    ";
        try {
            System.out.println(StringTextHandler.removeAllCharacters(text));
            System.out.println(CharTextHandler.removeAllCharacters(text));
            System.out.println(RegularExpressionTextHandler.removeAllCharacters(text));
        } catch (NoSuchTextException e) {
            e.printStackTrace();
        }

        text = "   Fojd,    Hkd,    Pro,    jkds,   jkh.  ";
        try {
            System.out.println(StringTextHandler.removeWordsStartingWithConsonant(text, 3));
            System.out.println(CharTextHandler.removeWordsStartingWithConsonant(text, 3));
            System.out.println(RegularExpressionTextHandler.removeWordsStartingWithConsonant(text, 3));
        } catch (NoSuchTextException e) {
            e.printStackTrace();
        } catch (TextHandlerIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }
}