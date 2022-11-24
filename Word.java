package a1_2001040219;
import java.io.File;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.HashSet;

public class Word {

    public static Set<String> stopWords = new HashSet<>();
    public String textWord;

    public Word(){
        this.textWord = null;
    }

    public boolean isKeyword(){
        boolean validword = checkWord();
        if (validword) {
            return true;
        }
        return false;
    }

    public boolean checkWord() {
        boolean validword = false;

        Matcher checkletter = Pattern.compile("[a-zA-Z]").matcher(this.textWord);
        Matcher checknumber = Pattern.compile("[0-9]").matcher(this.textWord);
        Matcher checkspace = Pattern.compile("\\s").matcher(this.textWord);

        if(!checknumber.find()&&!checkspace.find()){
            if(checkletter.find()&&!stopWords.contains(this.textWord.toLowerCase())){
                validword= true;
                return validword;
            }
        }else {
            validword =  false;
            return validword;
        }
        return validword;

    }
    public String getPrefix(){
        String prefix  = "";
        Matcher checkSpecial = Pattern.compile("^'-").matcher(this.textWord);
        Matcher checkFirstChar = Pattern.compile("^[\"(«<]").matcher(this.textWord);
        if(checkSpecial.find()){
            return prefix;
        }
        if(checkFirstChar.find()&&Word.createWord(this.textWord).isKeyword()){
            prefix =  Character.toString(this.textWord.charAt(0));
            return  prefix;
        }
        return prefix;

    }
    public String getSuffix(){
        String suffix = "";
        int index = 0;
        String word = this.textWord;
        Matcher checksemi = Pattern.compile("^'").matcher(word);
        Matcher checkletter = Pattern.compile("[^a-zA-Z0-9]$").matcher(word);
        Matcher checksemicolum = Pattern.compile("'").matcher(word);

        if(checksemi.find()){
            return suffix;
        }
        if(checksemicolum.find()&&Word.createWord(word).isKeyword()){
            index = word.indexOf("'");

            suffix =  word.substring(index);
            return  suffix;
        }
        if(checkletter.find()&&Word.createWord(word).isKeyword()){
//            int index=0;
            for(int i = word.length()-1;i>=0;i--){
                if(Character.isLetter(word.charAt(i))){
                    index= i;
                    break;
                }
            }
            suffix = word.substring(index+1);
            return  suffix;

        }
        return suffix;
    }

    public int getSufLen() {
        return this.getSuffix().length();
    }


    public int getPreLen() {
        return this.getPrefix().length();
    }

    public String getText(){
        Word thisWord = Word.createWord(this.textWord);
        String prefix = null;
        String suffix = null;
        String word = null;
        String finalWord = null;
        String root = this.textWord;
        if(!thisWord.isKeyword()){
            return root;
        }
        if(thisWord.getPrefix()!=null){
            if(thisWord.getSuffix()!=null){

                // no prefix and suffix
//                prefix = null;
//                suffix = null;
                word = root.substring(thisWord.getPreLen(), root.length()-thisWord.getSufLen());
                finalWord =  word;
                return finalWord;
            }else{


                word = root.substring(thisWord.getPreLen());
                return word;
            }
        }else{

            if(thisWord.getSuffix()!=null){
                word =  root.substring(0, root.length()-thisWord.getPreLen());
                return word;
            }
            word = root;
            return word;
        }
    }

    public boolean equals(Object o){
        Word thisWord = Word.createWord(this.textWord);
        if(!thisWord.getText().equalsIgnoreCase(((Word) o).getText())){
            return false;
        }
        return  true;
    }

    public String toString(){
        return this.textWord;
    }

    public static Word createWord(String rawText){
        Word newWord = new Word();
        newWord.textWord = rawText;
        return newWord;
    }


    public static boolean loadStopWords(String fileName) {

        try {

            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                stopWords.add(reader.nextLine());
            }

        }catch(Exception e){
            return false;
        }
        return true;
    }


}


