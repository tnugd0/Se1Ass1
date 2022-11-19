package engine;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> title = new ArrayList<>();
    private  List<Word> body = new ArrayList<>();

    public Doc(String content) {
        String [] wordList  = content.split("\n");

        String [] titleIndex = wordList[0].split(" ");
        String [] bodyIndex = wordList[1].split(" ");

        for (int i = 0; i < titleIndex.length; i++) {
            this.title.add(Word.createWord(titleIndex[i]));
        }

        for (int i = 0; i < bodyIndex.length; i++) {
            this.title.add(Word.createWord(bodyIndex[i]));
        }


    }



    public List<Word> getTitle()
    {
        return this.title;
    }

    public List<Word> getBody() {
        return this.body;

    }

    public boolean equals(Object o){
        if (!(o instanceof  Doc d)) {
            return false;
        }

        int titlematch = 0;
        int bodymatch = 0;

        if (this.title.size() != d.title.size()  || this.body.size() != d.body.size()  ) {
            return false;
        }


        for (int i = 0; i < this.title.size(); i++) {
            if (this.title.get(i).equals(d.title.get(i))) {
                titlematch += 1;
            }
        }


        for (int i = 0; i < this.body.size(); i++) {
            if (this.body.get(i).equals(d.body.get(i))) {
                bodymatch += 1;
            }
        }

        if (titlematch == this.title.size() && bodymatch == this.body.size()) {
            return true;
        }

        return  true;

    }




}
