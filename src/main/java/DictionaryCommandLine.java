public class DictionaryCommandLine extends Dictionary {
    public static String showAllWords() {
        String ans = "";
        System.out.printf("%-6s%c %-15s%c %-20s%n","No", '|' ,"English", '|', "Vietnamese");
        for (int i = 0; i < words.size(); i++) {
            String index = String.valueOf(i+1);
            System.out.printf("%-6d%c %-15s%c %-15s%n",i+1,'|', words.get(i).getWord_target(),'|',words.get(i).getWord_meaning());
        }
        return ans;
    }

    public static String dictionaryBasic() {
        DictionaryManagement.insertFromCommandLine();
        return new String(showAllWords());
    }

    public static String dictionaryAdvanced(){
        DictionaryManagement.insertFromFile();
        return new String(showAllWords());
    }
    public static void main(String[] args) {
        //System.out.println(dictionaryAdvanced());
        DictionaryManagement.dictionaryLookUp();
    }
}
