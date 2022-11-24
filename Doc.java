package a1_2001040219;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Doc {
    public String content;
    List<Word> title;
    List<Word> body;

    public Doc(String content){
        this.content = content;
    }

    public List<Word> getTitle(){
        this.title= new LinkedList<>();
        String[] wordsList = this.content.split("\n")[0].split("\\s");
        this.title = Arrays.stream(wordsList).map(Word::createWord).collect(Collectors.toList());
        return this.title;
    }

    public List<Word> getBody(){
        this.body= new LinkedList<>();
        String[] wordsList = this.content.split("\n")[1].split("\\s");
        this.body = Arrays.stream(wordsList).map(Word::createWord).collect(Collectors.toList());
        return this.body;
    }

    public boolean equals(Object o){
        if(o!=null){
            return true;
        }

        if (o instanceof Doc d) {
            if(!((Doc) o).getBody().equals(this.getBody()) && ((Doc) o).getTitle().equals(this.getTitle()) ){
                return false;
            }
        }


        return true;

    }




}
