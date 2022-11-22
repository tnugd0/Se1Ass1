package engine;

import java.util.List;

public class Result implements Comparable<Result> {
    private Doc d;
    private List<Match> matches;
//    private int matchCount = matches.size();


    public Result(Doc d, List<Match> matches) {
        this.d = d;
        this.matches = matches;
    }

    public Doc getDocs() {
        return this.d;
    }

    public List<Match> getMatches() {

        return this.matches;
    }


    public int getTotalFrequency() {
        int count = 0;
        for (Match match : matches) {
            count += match.getFreq();
        }

        return count;

    }

    public double getAverageFirstIndex() {
        int sum = 0;
//        int count = 0;
        for (Match match : matches) {
            sum += match.getFirstIndex();
//            count++;
        }

        return sum / this.matchesCount();
    }

    public String htmlHighLight() {
        List<Word> titleList = this.d.getTitle();
        List<Word> bodyList = this.d.getBody();

        for (int index = 0; index < this.matches.size(); index++) {
            if (this.matches.get(index).getFreq() > 0) {
                for (Word title : titleList) {
                    if (this.matches.get(index).getWord().equals(title)) {
                        titleList.set(titleList.indexOf(title), Word.createWord(title.getPrefix() + "<u>" + title.getText() + "</u>" + title.getSuffix()));
                    }
                }
                for (Word body : bodyList) {
                    if (this.matches.get(index).getWord().equals(body)) {
                        bodyList.set(bodyList.indexOf(body), Word.createWord(body.getPrefix() + "<u>" + body.getText() + "</u>" + body.getSuffix()));
                    }
                }
            }
        }

        String title = "";
        for (Word word : titleList) {
            title = title + word.toString() + " ";
        }
        String body = "";
        for (Word word : bodyList) {
            body = body + word.toString() + " ";
        }

        return String.format("<h3>%s</h3><p>%s</p>".format(title.trim(), body.trim()));
    }


    public int matchesCount() {
        return this.matches.size();
    }


    public int compareTo(Result o) {

        if (this.matchesCount() > o.matchesCount()) {
            return 1;
        }
        if (this.matchesCount() < o.matchesCount()) {
            return -1;
        }
        if (this.getTotalFrequency() > o.getTotalFrequency()) {
            return 1;
        }
        if (this.getTotalFrequency() < o.getTotalFrequency()) {
            return -1;
        }

        if (this.getAverageFirstIndex() > o.getAverageFirstIndex()) {
            return 1;
        }

        return -1;
    }


}
