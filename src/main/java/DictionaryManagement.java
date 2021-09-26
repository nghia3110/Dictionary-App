import java.io.*;
import java.nio.file.FileVisitResult;
import java.util.Collection;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    private static final String URL_PATH = "src\\main\\java\\dictionaries.txt";
    private static final String OUT_URL_PATH = "src\\main\\java\\dictionaries_out.txt";

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

    public static void dictionaryExportToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(OUT_URL_PATH);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String formatBody = "%-15s %-15s%n";
            for (int i = 0; i < words.size(); i++) {
                bufferedWriter.write(String.format(formatBody,words.get(i).getWord_target(), words.get(i).getWord_meaning()));
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeWordFromFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(URL_PATH);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (int i = 0; i < words.size(); i++) {
                bufferedWriter.write(words.get(i).getWord_target() + "," + words.get(i).getWord_meaning() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
