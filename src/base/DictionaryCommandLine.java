package base;

import java.io.IOException;

public class DictionaryCommandLine extends Dictionary {
    public static String showAllWords() {
        String ans = "";
        System.out.printf("%-6s%c %-15s%c %-20s%n","No", '|' ,"English", '|', "Vietnamese");
        for (int i = 0; i < oldVocab.size(); i++) {
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', oldVocab.get(i).getSearching(), '|',oldVocab.get(i).getMeaning());
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        DictionaryManagement.insertFromFile();
        DictionaryManagement.dictionaryLookUp();
        // System.out.println(showAllWords());
    }
}
