package engine;

public class Match  implements  Comparable<Match>{
    private Doc doc;
    private Word word;
    private int freq;
    private int firstIndex;


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
        if (firstIndex > o.firstIndex) return 1;
        if (firstIndex < o.firstIndex) return -1;
        return 0;

    }
}
