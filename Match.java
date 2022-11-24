package a1_2001040219;

public class Match  implements  Comparable<Match>{
    Doc doc;
    Word word;
    int freq;
    int firstIndex;


    public Match(Doc d, Word w, int freq, int firstIndex) {
        this.doc = d;
        this.word = w;
        this.freq = freq;
        this.firstIndex = firstIndex;
    }
    public Word getWord() {
        return this.word;
    }
    public int getFreq() {
        return this.freq;
    }
    public int getFirstIndex() {
        return this.firstIndex;
    }

    public int compareTo( Match o) {
        if (this.getFirstIndex() > o.getFirstIndex()) {
            return 1;
        }
        if (this.getFirstIndex() < o.getFirstIndex()) {
            return -1;
        }
        return 0;

    }
}
