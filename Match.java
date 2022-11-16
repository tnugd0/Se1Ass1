package engine;

public class Match {
    private Doc d;
    private Word w;
    private int freq;
    private int firstIndex;


    public Match(Doc d, Word w, int freq, int firstIndex) {
        this.d = d;
        this.w = w;
        this.freq = freq;
        this.firstIndex = firstIndex;
    }


    public Word getWord() {
        return w;
    }

    public int getFreq() {
        return freq;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int compareTo( Match o) {
        return 0;
    }
}
