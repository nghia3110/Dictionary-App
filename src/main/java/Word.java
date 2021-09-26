
public class Word implements Comparable<Word> {
    private String word_target;
    private String word_meaning;

    public Word(){
        word_target = "";
        word_meaning = "";
    }
    public Word(String word_target, String word_meaning){
        this.word_target = word_target;
        this.word_meaning = word_meaning;
    }
    public String getWord_target() {
        return word_target;
    }
    public String getWord_meaning() {
        return word_meaning;
    }
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }
    public void setWord_meaning(String word_meaning) {
        this.word_meaning = word_meaning;
    }
   public int compareTo(Word w) {
      return this.word_target.compareToIgnoreCase(w.word_target);
    }
}
