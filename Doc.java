package engine;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> title = new ArrayList<>();
    private  List<Word> body = new ArrayList<>();

    public Doc(String content) {
        String [] wordList  = content.split("\n");

        String title = wordList[0];
        String body = wordList[1];






    }

    public List<Word> getTitle()
    {
        return this.title;
    }

    public List<Word> getBody() {
        return this.body;

    }

    public boolean equals(Object o){
        return true;
    }




}
