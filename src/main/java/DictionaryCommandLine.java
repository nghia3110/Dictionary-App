public class DictionaryCommandLine extends Dictionary {
    public static String showAllWords() {
        String ans = "";
        ans += ("No   " + "| English       " + "| Vietnamese" + "\n");
        for (int i = 0; i < words.size(); i++) {
            String index = String.valueOf(i+1);
            ans += (index + "    | " + words.get(i).getWord_target() + "            | " + words.get(i).getWord_meaning() + "\n");
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
        System.out.println(dictionaryAdvanced());
    }
}
