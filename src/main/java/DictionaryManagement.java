import java.io.*;
import java.nio.file.FileVisitResult;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    private static final String URL_PATH = "src\\main\\java\\Dictionaries.txt";

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
            Collections.sort(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addWord(String Word_target, String Word_explain) {
        Word w = new Word(Word_target.toLowerCase(Locale.ROOT), Word_explain.toLowerCase(Locale.ROOT));
        words.add(w);
        Collections.sort(words);
    }

    public static void removeWord(String Word_target) {
        int pos = -1;
        pos = Collections.binarySearch(words, new Word(Word_target, null));
        if (pos >= 0) {
            words.remove(words.get(pos));
        } else {
            System.out.println("Từ bạn cần xoá không có trong từ điển.");
        }
    }

    public static void modifyWord(String Word_target, String Word_meaning) {
        int pos = -1;
        pos = Collections.binarySearch(words, new Word(Word_target, null));
        if (pos >= 0) {
            words.get(pos).setWord_meaning(Word_meaning);
        } else {
            System.out.println("Không tìm thấy từ bạn muốn sửa đổi.");
        }
    }

}
