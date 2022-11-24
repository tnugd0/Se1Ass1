package a1_2001040219;

import java.util.List;

public class Result implements Comparable<Result> {
    public Doc d;
    public List<Match> matches;
    public Result(Doc d, List<Match> matches){
        this.d =d;
        this.matches = matches;
    }
    public List<Match> getMatches(){
        return this.matches;
    }
    public Doc getDoc(){
        return this.d;
    }
    public int getTotalFrequency(){
        int total = 0;
        for(Match match :this.matches){
            total += match.getFreq();
        }
        return total;


    }
    public double getAverageFirstIndex(){
        double total =0;
        for(Match match : this.matches){
            total = total + match.getFirstIndex();
        }
        return total/(this.getMatchesCount());
    }
    public String htmlHighlight(){
        List<Word> titleList = this.d.getTitle();
        List<Word> bodyList = this.d.getBody();

        for(int index = 0; index< this.matches.size(); index+=1){
            if(this.matches.get(index).getFreq()>0){
                for (int i = 0; i < titleList.size(); i++) {
                    if(this.matches.get(index).getWord().equals(titleList.get(i))){
                        titleList.set(titleList.indexOf(titleList.get(i)), Word.createWord(titleList.get(i).getPrefix()+"<u>"+titleList.get(i).getText()+"</u>"+titleList.get(i).getSuffix()));
                    }
                }

                for (int i = 0; i < bodyList.size(); i++) {
                    if(this.matches.get(index).getWord().equals(bodyList.get(i))){
                        bodyList.set(bodyList.indexOf(bodyList.get(i)), Word.createWord(bodyList.get(i).getPrefix()+"<b>"+bodyList.get(i).getText()+"</b>"+bodyList.get(i).getSuffix()));
                    }
                }
            }
        }

        StringBuilder title= new StringBuilder();
        for (int i = 0; i < titleList.size(); i++) {
            title.append(titleList.get(i).toString()).append(" ");
        }
        StringBuilder body= new StringBuilder();
        for (int i = 0; i < bodyList.size(); i++) {
            body.append(bodyList.get(i).toString()).append(" ");
        }


        String returnString = String.format("<h3>%s</h3><p>%s</p>",title.toString().trim(),body.toString().trim());
        return returnString;
    }


    public int getMatchesCount() {
        return this.matches.size();
    }

    public int compareTo(Result o){
        if(this.getMatchesCount()>o.getMatches().size()){
            return -1;
        }
        if(this.getMatchesCount()<o.getMatches().size()){
            return 1;
        }
        if(this.getTotalFrequency()>o.getTotalFrequency()){
            return -1;
        }
        if(this.getTotalFrequency()<o.getTotalFrequency()){
            return 1;
        }
        if(this.getAverageFirstIndex()>o.getAverageFirstIndex()){
            return 1;
        }
        if(this.getAverageFirstIndex()<o.getAverageFirstIndex()){
            return -1;
        }
        return 0;
    }
}


