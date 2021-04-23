package by.epamtc.service;

import by.epamtc.exception.WrongFileNameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReader {

    public static String readTextFromFile(String fileName) throws FileNotFoundException, WrongFileNameException {
        if (fileName == null || fileName.isEmpty()) throw new WrongFileNameException("Invalid file name");
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
        }

        return stringBuilder.toString();
    }

    public static String readTextFromConsole() {
        Scanner sc = new Scanner(System.in);

        String text = "";

        while (!sc.hasNextLine()) {
            sc.next();
        }

        text = sc.nextLine();

        return text;
    }

}
