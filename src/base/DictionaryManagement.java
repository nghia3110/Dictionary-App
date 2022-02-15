package base;

import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    private static final String IN_PATH = "src/main/resource/oldVocab/dictionaries.txt";
    private static final String OUT_PATH = "src/main/resource/oldVocab/dictionaries_out.txt";


    public static void insertFromCommandLine() {
        Scanner getStringInput = new Scanner(System.in);
        Scanner getIntegerInput = new Scanner(System.in);
        int size = getIntegerInput.nextInt();
        int i = 0;
        while (i < size) {
            String target = getStringInput.nextLine();
            String meaning = getStringInput.nextLine();
            Word temp = new Word(target, meaning);
            oldVocab.add(temp);
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
                oldVocab.add(temp);

            }
            Collections.sort(oldVocab);
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
            for (Word word : oldVocab) {
                bufferedWriter.write(String.format(format, word.getSearching(), word.getMeaning()));
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateWordToFile() {
        try {
            FileWriter fileWriter = new FileWriter(IN_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : oldVocab) {
                bufferedWriter.write(word.getSearching() + "," + word.getMeaning() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addWord(String searching, String meaning) {
        searching = searching.toLowerCase();
        meaning = meaning.toLowerCase();
        int posAddWord = binaryCheck(0, oldVocab.size(), searching);
        if (posAddWord == -1) {
            System.out.println("Từ bạn thêm đã tồn tại. Hãy chọn chức năng sửa đổi!");
            return;
        }
        oldVocab.add(new Word());
        for (int i = oldVocab.size() - 2; i >= posAddWord; i--) {
            oldVocab.get(i + 1).setSearching(oldVocab.get(i).getSearching());
            oldVocab.get(i + 1).setMeaning(oldVocab.get(i).getMeaning());
        }
        oldVocab.get(posAddWord).setSearching(searching);
        oldVocab.get(posAddWord).setMeaning(meaning);
        updateWordToFile();
    }

    public static void removeWord(String searching) {
        searching = searching.toLowerCase();
        int index = Collections.binarySearch(oldVocab, new Word(searching, null));
        if (index >= 0) {
            oldVocab.remove(oldVocab.get(index));
        } else {
            System.out.println("Từ bạn cần xoá không có trong từ điển!");
        }
        updateWordToFile();
    }

    public static void modifyWord(String searching, String meaning) {
        searching = searching.toLowerCase();
        meaning = meaning.toLowerCase();
        int pos = -1;
        pos = Collections.binarySearch(oldVocab, new Word(searching, null));
        if (pos >= 0) {
            oldVocab.get(pos).setMeaning(meaning);
        } else {
            System.out.println("Không tìm thấy từ bạn muốn sửa đổi!");
        }
        updateWordToFile();
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
        int compareNext = word.compareTo(oldVocab.get(mid).getSearching());
        if (mid == 0) {
            if (compareNext < 0) {
                return 0;
            } else if (compareNext > 0) {
                return binaryCheck(mid + 1, end, word);
            } else {
                return -1;
            }
        } else {
            int comparePrevious = word.compareTo(oldVocab.get(mid - 1).getSearching());
            if (comparePrevious > 0 && compareNext < 0) {
                return mid;
            } else if (comparePrevious < 0) {
                return binaryCheck(start, mid - 1, word);
            } else if (compareNext > 0) {
                if (mid == oldVocab.size() - 1) {
                    return oldVocab.size();
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
        int compare = isContain(word, oldVocab.get(mid).getSearching());
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
            if (isContain(word, oldVocab.get(j).getSearching()) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            Word temp = new Word(oldVocab.get(i).getSearching(), oldVocab.get(i).getMeaning());
            listWordSearching.add(temp);
        }

        for (int i = index + 1; i < oldVocab.size(); i++) {
            if (isContain(word, oldVocab.get(i).getSearching()) == 0) {
                Word temp = new Word(oldVocab.get(i).getSearching(), oldVocab.get(i).getMeaning());
                listWordSearching.add(temp);
            } else {
                break;
            }
        }
        for (Word wordSearching : listWordSearching) {
            System.out.println(wordSearching.getSearching());
        }
    }

    public static void dictionaryLookUp() throws IOException {
        Scanner getInput = new Scanner(System.in);
        String word = getInput.nextLine().toLowerCase();
        int index = binaryLookup(0, oldVocab.size(), word);
        if (index < 0) {
            Spelling corrector = new Spelling("src/resource/vocab/spelling.txt");
            word = corrector.correct(word);
            index = binaryLookup(0, oldVocab.size(), word);
        }
        showWordLookup(word, index);
    }
}

