import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    static ArrayList<Word> words = new ArrayList<Word>();
    static HashMap<String,String> lookUp = new HashMap<String,String>();
    public static void addWordToMap(){
        int i = 0;
        while(i < words.size()){
            lookUp.put(words.get(i).getWord_target(), words.get(i).getWord_meaning());
            i++;
        }
    }
}
