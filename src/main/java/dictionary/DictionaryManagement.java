package dictionary;

import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    private static final String IN_PATH = "src/main/resource/vocab/dictionaries.txt";
    private static final String OUT_PATH = "src/main/resource/vocab/dictionaries_out.txt";
    private static final String ENG_VIE_PATH = "src/main/resource/vocab/test.txt";
    private static final String SPLITTING_PATTERN = "<html>";

    public static void insertFromCommandLine() {
        Scanner getStringInput = new Scanner(System.in);
        Scanner getIntegerInput = new Scanner(System.in);
        int size = getIntegerInput.nextInt();
        int i = 0;
        while (i < size) {
            String target = getStringInput.nextLine();
            String meaning = getStringInput.nextLine();
            Word temp = new Word(target, meaning);
            vocab.add(temp);
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
                vocab.add(temp);
            }
            Collections.sort(vocab);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(IN_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SPLITTING_PATTERN);
                String word = parts[0];
                String definition = SPLITTING_PATTERN + parts[1];
                Word wordObj = new Word(word, definition);
                vocab.add(wordObj);
            }
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
            for (Word word : vocab) {
                bufferedWriter.write(String.format(format, word.getSearching(), word.getMeaning()));
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeWordInFile() {
        try {
            FileWriter fileWriter = new FileWriter(ENG_VIE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : vocab) {
                bufferedWriter.write(word.getSearching() + "," + word.getMeaning() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checklookup(String searching) {
        searching = searching.toLowerCase();
        int posAddWord = binaryCheck(0, vocab.size(), searching);
        if (posAddWord == -1) {

            return false;
        }
        return true;
    }

    public static void addWord(String searching, String meaning) {
        searching = searching.toLowerCase();
        meaning = meaning.toLowerCase();
        int posAddWord = binaryCheck(0, vocab.size(), searching);
        if (posAddWord == -1) {
            System.out.println("Từ bạn thêm đã tồn tại. Hãy chọn chức năng sửa đổi!");
            return;
        }
        vocab.add(new Word());
        for (int i = vocab.size() - 2; i >= posAddWord; i--) {
            vocab.get(i + 1).setSearching(vocab.get(i).getSearching());
            vocab.get(i + 1).setMeaning(vocab.get(i).getMeaning());
        }
        vocab.get(posAddWord).setSearching(searching);
        vocab.get(posAddWord).setMeaning(meaning);
        changeWordInFile();
    }

    public static void removeWord(String searching) {
        searching = searching.toLowerCase();
        int index = Collections.binarySearch(vocab, new Word(searching, null));
        System.out.println(index);
        if (index >= 0) {
            vocab.remove(vocab.get(index));
        } else {
            System.out.println("Từ bạn cần xoá không có trong từ điển!");
        }
        changeWordInFile();
    }

    public static void modifyWord(String searching, String meaning) {
        searching = searching.toLowerCase();
        meaning = meaning.toLowerCase();
        int pos = -1;
        pos = Collections.binarySearch(vocab, new Word(searching, null));
        if (pos >= 0) {
            vocab.get(pos).setMeaning(meaning);
        } else {
            System.out.println("Không tìm thấy từ bạn muốn sửa đổi!");
        }
        changeWordInFile();
    }

    public static int isContain(String str1, String str2) {
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                return 1;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                return -1;
            }
        }
        if (str1.length() > str2.length()) {
            return 1;
        }
        return 0;
    }

    public static int binaryCheck(int start, int end, String word) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        int compareNext = word.compareTo(vocab.get(mid).getSearching());
        if (mid == 0) {
            if (compareNext < 0) {
                return 0;
            } else if (compareNext > 0) {
                return binaryCheck(mid + 1, end, word);
            } else {
                return -1;
            }
        } else {
            int comparePrevious = word.compareTo(vocab.get(mid - 1).getSearching());
            if (comparePrevious > 0 && compareNext < 0) {
                return mid;
            } else if (comparePrevious < 0) {
                return binaryCheck(start, mid - 1, word);
            } else if (compareNext > 0) {
                if (mid == vocab.size() - 1) {
                    return vocab.size();
                }
                return binaryCheck(mid + 1, end, word);
            } else {
                return -1;
            }
        }
    }

    public static int binaryLookup(int start, int end, String word) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        int compare = isContain(word, vocab.get(mid).getSearching());
        if (compare == -1) {
            return binaryLookup(start, mid - 1, word);
        } else if (compare == 1) {
            return binaryLookup(mid + 1, end, word);
        } else {
            return mid;
        }
    }

    public static void showWordLookup(String word, int index) {
        if (index < 0) {
            //System.out.println((new Spelling.("src/main/java/big.txt")).correct(word.toLowerCase()));
            return;
        }
        ArrayList<Word> listWordSearching = new ArrayList<Word>();
        int j = index;
        while (j >= 0) {
            if (isContain(word, vocab.get(j).getSearching()) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            Word temp = new Word(vocab.get(i).getSearching(), vocab.get(i).getMeaning());
            listWordSearching.add(temp);
        }
        for (int i = index + 1; i < vocab.size(); i++) {
            if (isContain(word, vocab.get(i).getSearching()) == 0) {
                Word temp = new Word(vocab.get(i).getSearching(), vocab.get(i).getMeaning());
                listWordSearching.add(temp);
            } else {
                break;
            }
        }
        for (Word wordSearching : listWordSearching) {
            System.out.println(wordSearching.getSearching());
        }
    }

    public static void dictionaryLookUp() {
        Scanner getInput = new Scanner(System.in);
        String word = getInput.nextLine().toLowerCase();
        int index = binaryLookup(0, vocab.size(), word);
        showWordLookup(word, index);
    }
}
