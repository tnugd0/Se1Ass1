package a1_2001040219;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Query{
    private final String query;
    public Query(String query){
        this.query = query;
    }

    public List<Word> getKeywords(){
        List<Word> keyList = new LinkedList<>();

        for (int i = 0; i < this.query.split(" ").length; i++) {
            String word = this.query.split(" ")[i];
            if (Word.createWord(word).isKeyword()) {
                keyList.add(Word.createWord(word));
            }
        }
        return keyList;
    }
    public List<Match> matchAgainst(Doc d){
        List<Match> matchesList = new ArrayList<>();
        List<Word> keyWords = this.getKeywords();
        List<Word> wordList = new ArrayList<>();
        wordList = d.getTitle();
        wordList.addAll(d.getBody());


        for(int index = 0; index < keyWords.size(); index +=1 ){
            int frequency =0;
            int first = 0;
            for (int i = 0; i < wordList.size(); i++) {
                if (keyWords.get(index).equals(wordList.get(i))) {
                    first = wordList.indexOf(wordList.get(i));
                    frequency +=1;
                }
            }


            Match match = new Match(d, keyWords.get(index), frequency, first);
            if(match.getFreq()!=0){
                matchesList.add(match);
            }

        }

        List<Match> listResult = matchesList.stream().sorted().collect(Collectors.toList());
        return listResult;
    }

}

