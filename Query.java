package engine;
import java.util.ArrayList;
import java.util.List;

public class Query {

//    private String searchPharse;
    private List<Word> keyWordsList = new ArrayList<>();

    public Query(String searchPharse) {
        String [] list = searchPharse.split(" ");
        for (String key: list) {
            Word word = Word.createWord(key);
            if (word.isKeyword()) {
                keyWordsList.add(word);
            }
        }
    }
    public List<Word> getKeywords() {
        return  this.keyWordsList;
    }


    public List<Match>  matchAgainst(Doc d) {

        List<Match> listmatch = new ArrayList<>();
        List<Word> wordlist = new ArrayList<>();

        for (Word w : d.getTitle()) {
            wordlist.add(w);
        }

        for (Word w : d.getBody()) {
            wordlist.add(w);
        }

        return listmatch;


    }
}
