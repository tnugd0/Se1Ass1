package engine;

import java.util.List;

public class Query {

    private String searchPharse;

    public Query(String searchPharse) {
        this.searchPharse = searchPharse;
    }

    public List<Word> getKeywords() {
        return  null;
    }

    public List<Match>  matchAgainst(Doc d) {
        return null;
    }




}
