package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Query{
    private String searchPahse;
    public Query(String searchPhase){
        this.searchPahse = searchPhase;
    }
    public List<Word> getKeywords(){
        List<Word> keyWords = new ArrayList<Word>();
        String[] words = this.searchPahse.split("\\s");
        for(String word:words){
            Word someWord = Word.createWord(word);
            if(someWord.isKeyword()){
                keyWords.add(someWord);
            }
        }
        return keyWords;
    }
    public List<Match> matchAgainst(Doc d){
        List<Match> listMatch = new ArrayList<Match>();
        List<Word> keyWords = this.getKeywords();
        List<Word> words = new ArrayList<Word>();
        words = d.getTitle();
        words.addAll(d.getBody());
        for(Word keyWord : keyWords){
            int freq =0;
            int firstIndex = 0;
            for(Word word:words){
                if(keyWord.equals(word)){
                    firstIndex = words.indexOf(word);
                    freq = freq+1;
                }
            }
            Match match = new Match(d, keyWord, freq, firstIndex);
            if(match.getFreq()!=0){
                listMatch.add(match);
            }

        }
        Collections.sort(listMatch);
        return listMatch;
    }

}

