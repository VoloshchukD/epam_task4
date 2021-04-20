package by.epamtc.run;

import by.epamtc.service.CharTextHandler;
import by.epamtc.service.RegularExpressionHandler;
import by.epamtc.service.StringTextHandler;

public class Runner {
    public static void main(String[] args) {
        String text = "hello, ih, myd- pi.";
        System.out.println(StringTextHandler.replaceEachWordLetter(text, "P", 3));
        System.out.println(CharTextHandler.replaceEachWordLetter(text, 'P', 3));

        String text2 = "hfPA, sfhjP, sflkjPO";
        System.out.println(StringTextHandler.correctWrongLetter(text2));
        System.out.println(CharTextHandler.correctWrongLetter(text2));

        String text3 = "hfP, sfhd, sfg, dsdaf.";
        System.out.println(StringTextHandler.replaceWordsWithSubstring(text3, "AAAAA", 3));
        System.out.println(CharTextHandler.replaceWordsWithSubstring(text3, "AAAAA", 3));

        String text4 = " hello, its, me.  ";
        System.out.println(StringTextHandler.removeAllCharacters(text4));
        System.out.println(CharTextHandler.removeAllCharacters(text4));
        System.out.println(RegularExpressionHandler.removeAllCharacters(text4));

        String text5 = "фыва, фыа, про, лкец, зло.";
        System.out.println(StringTextHandler.removeWordsStartingWithConsonant(text5, 3));
        System.out.println(CharTextHandler.removeWordsStartingWithConsonant(text5, 3));

    }
}