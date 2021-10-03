package dictionary;

public class DictionaryCommandLine extends Dictionary {
    public static String showAllWords() {
        String ans = "";
        System.out.printf("%-6s%c %-15s%c %-20s%n","No", '|' ,"English", '|', "Vietnamese");
        for (int i = 0; i < vocab.size(); i++) {
            String index = String.valueOf(i + 1);
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', vocab.get(i).getSearching(), '|',vocab.get(i).getMeaning());
        }
        return ans;
    }

    public static void main(String[] args) {
        dictionary.DictionaryManagement.insertFromFile();
        //DictionaryManagement.loadDataFromFile();
        DictionaryManagement.modifyWord("aaa","bbb");
        //dictionary.DictionaryManagement.addWord("BABY","zzz");
        //System.out.println(showAllWords());
    }
}
