import java.io.*;
import java.nio.file.FileVisitResult;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    private static final String URL_PATH = "C:\\Users\\Asus\\IdeaProjects\\Dictionary-App\\src\\main\\java\\Dictionaries.txt";
    public static void insertFromCommandLine() {
        Scanner getStringInput = new Scanner(System.in);
        Scanner getIntegerInput = new Scanner(System.in);
        int size = getIntegerInput.nextInt();
        int i = 0;
        while (i < size) {
            String target = getStringInput.nextLine();
            String meaning = getStringInput.nextLine();
            Word temp = new Word(target, meaning);
            words.add(temp);
            i++;
        }
    }

    public static void insertFromFile() {
        try {
            File inFile = new File(URL_PATH);
            FileReader fileReader = new FileReader(inFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] wordsInLine = line.split(",");
                Word temp = new Word(wordsInLine[0], wordsInLine[1]);
                words.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String dictionaryLookUp() {
        Scanner getInput = new Scanner(System.in);
        String target = getInput.nextLine();
        return lookUp.get(target);
    }

    public static void addNewWord(){
        Scanner getInput = new Scanner(System.in);
        String target = getInput.nextLine();
        String meaning = getInput.nextLine();
        Word newWord = new Word(target,meaning);
        words.add(newWord);
    }

    public static void removeWord(){
        Scanner getInput = new Scanner(System.in);
        String target = getInput.nextLine();
        
    }

}
