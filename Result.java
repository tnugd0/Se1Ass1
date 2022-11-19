package engine;

import java.util.List;

public class Result implements Comparable<Result> {
    private Doc d;
    private List<Match> matches;
    private int mathCount = matches.size();
    private int totalFrequency;
    private int averagefirstindex;


    public Result(Doc d, List<Match> matches) {
        this.d = d;
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }


    public int getTotalFrequency() {
        return 0;
    }

    public double getAverageFirstIndex() {
        return 0 ;
    }

    public  String htmlHighLight() {
        return null;
    }


    public int compareTo(Result o) {
        return 0;
    }



}
