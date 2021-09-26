import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    private static final String IN_PATH = "src/main/java/dictionaries.txt";
    private static final String OUT_PATH = "src/main/java/dictionaries_out.txt";
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
            File inFile = new File(IN_PATH);
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

    public static void exportWordToFile() {
        try {
            File file = new File(OUT_PATH);
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String format = "%-15s %-15s%n";
            for (int i = 0; i < words.size(); i++) {
                bufferedWriter.write(String.format(format, words.get(i).getWord_target(), words.get(i).getWord_meaning()));
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeWordInFile(){
        try{
            FileWriter fileWriter = new FileWriter(IN_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int i = 0; i< words.size(); i++){
                bufferedWriter.write(words.get(i).getWord_target() + "," + words.get(i).getWord_meaning() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void addWord(String Word_target, String Word_explain) {
        Word w = new Word(Word_target.toLowerCase(Locale.ROOT), Word_explain.toLowerCase(Locale.ROOT));
        words.add(w);
        Collections.sort(words);
        changeWordInFile();
    }

    public static void removeWord(String Word_target) {
        int index = Collections.binarySearch(words, new Word(Word_target, null));
        System.out.println(index);
        if (index >= 0) {
            words.remove(words.get(index));
        } else {
            System.out.println("Từ bạn cần xoá không có trong từ điển!");
        }
        changeWordInFile();
    }

    public static void modifyWord(String Word_target, String Word_meaning) {
        int pos = -1;
        pos = Collections.binarySearch(words, new Word(Word_target, null));
        if (pos >= 0) {
            words.get(pos).setWord_meaning(Word_meaning);
        } else {
            System.out.println("Không tìm thấy từ bạn muốn sửa đổi!");
        }
        changeWordInFile();
    }

    public static byte isContain(String str1, String str2) {
        str1 = str1.toLowerCase();
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                return 1;
            }
            else if (str1.charAt(i) < str2.charAt(i)) {
                return -1;
            }
        }
        if (str1.length() > str2.length()) {
            return 1;
        }
        return 0;
    }

    public static int wordSearcher(int start, int end, String word) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        byte compare = isContain(word, words.get(mid).getWord_target());
        if (compare == -1) {
            return wordSearcher(start, mid - 1, word);
        } else if (compare == 1) {
            return wordSearcher(mid + 1, end, word);
        } else {
            return mid;
        }
    }

    public static void showWordSearch(String word, int index) {
        if (index < 0) {
            return;
        }
        ArrayList<Word> listWordSearch = new ArrayList<Word>();
        int j = index;
        while (j >= 0) {
            if (isContain(word, words.get(j).getWord_target()) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            Word temp = new Word(words.get(i).getWord_target(), words.get(i).getWord_meaning());
            listWordSearch.add(temp);
        }
        for (int i = index + 1; i < words.size(); i++) {
            if (isContain(word, words.get(i).getWord_target()) == 0) {
                Word temp = new Word(words.get(i).getWord_target(), words.get(i).getWord_meaning());
                listWordSearch.add(temp);
            }
            else {
                break;
            }
        }
        for (int i = 0; i < listWordSearch.size(); i++) {
            System.out.println(listWordSearch.get(i).getWord_target());
        }
    }

    public static void dictionaryLookUp() {
        Scanner getInput = new Scanner(System.in);
        String target = getInput.nextLine();
        DictionaryManagement.insertFromFile();
        int index = wordSearcher(0, words.size(), target);
        showWordSearch(target, index);
    }
}
