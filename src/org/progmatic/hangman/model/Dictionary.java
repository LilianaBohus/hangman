package org.progmatic.hangman.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.progmatic.hangman.Settings;

public class Dictionary {

    static List<String> listOfWords = new ArrayList<>();

    public static String getRandomWord() {
        readFromFile(Settings.FILE_TO_READ_WORDS_FROM);
        Random rand = new Random();
        return listOfWords.get(rand.nextInt(listOfWords.size()));
    }

    public static void readFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                listOfWords.add(scanner.next().trim());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }
}
