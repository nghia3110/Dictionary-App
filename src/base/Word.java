package base;

public class Word implements Comparable<Word> {
    private String searching;
    private String meaning;

    public Word(){
        searching = "";
        meaning = "";
    }

    public Word(String searching, String meaning){
        this.searching = searching;
        this.meaning = meaning;
    }

    public String getSearching() {
        return searching;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setSearching(String searching) {
        this.searching = searching;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int compareTo(Word other) {
        return this.searching.compareToIgnoreCase(other.searching);
    }
}

