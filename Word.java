package engine;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;

public class Word {

    public String textWord;
    public Word(){
        this.textWord = null;
    }

    public static Set<String> stopWords = new HashSet<String>();

    public boolean isKeyword(){
        boolean validword = checkWord();
        if (validword) {
            return true;
        }
        return false;
    }

    public boolean checkWord() {
        boolean validword = false;

        Matcher m1 = Pattern.compile("[a-zA-Z]{1}").matcher(this.textWord);
        Matcher m2 = Pattern.compile("[0-9]{1}").matcher(this.textWord);
        Matcher m3 = Pattern.compile("\\s").matcher(this.textWord);


        int m1Count = 0;


        if(!m2.find()&&!m3.find()){
            if(m1.find()&&!stopWords.contains(this.textWord.toLowerCase())){
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
        Word thisWord = Word.createWord(this.textWord);
        Matcher checkSpecial = Pattern.compile("^\'-").matcher(this.textWord);
        Matcher checkFirstChar = Pattern.compile("^[\"(«<]").matcher(this.textWord);
        if(checkSpecial.find()){
            return prefix;
        }
        if(checkFirstChar.find()&&thisWord.isKeyword()){
            prefix =  Character.toString(this.textWord.charAt(0));
            return  prefix;
        }
        return prefix;

    }
    public String getSuffix(){
        String suffix = "";
        Word thisWord = Word.createWord(this.textWord);
        Matcher matcher3 = Pattern.compile("^\'").matcher(this.textWord);
        Matcher matcher1 = Pattern.compile("[^a-zA-Z0-9]$").matcher(this.textWord);
        Matcher matcher2 = Pattern.compile("\'").matcher(this.textWord);

        if(matcher3.find()){
            return suffix;
        }
        if(matcher2.find()&&thisWord.isKeyword()){

            suffix =  this.textWord.substring(this.textWord.indexOf("\'"));
            return  suffix;
        }
        if(matcher1.find()&&thisWord.isKeyword()){
            int index=0;
            for(int i = this.textWord.length()-1;i>=0;i--){
                if(Character.isLetter(this.textWord.charAt(i))){
                    index= i;
                    break;
                }
            }
            suffix = this.textWord.substring(index+1);
            return  suffix;

        }
        return suffix;
    }

    public String getText(){
        Word thisWord = Word.createWord(this.textWord);
        String prefix = thisWord.getPrefix();
        String suffix = thisWord.getSuffix();
        if(!thisWord.isKeyword()){
            return this.textWord;
        }
        if(prefix!=null){
            if(suffix!=null){
                return this.textWord.substring(prefix.length(), this.textWord.length()-suffix.length());
            }else{
                return this.textWord.substring(prefix.length());
            }
        }else{
            if(suffix!=null){
                return this.textWord.substring(0, this.textWord.length()-suffix.length());
            } else{
                return this.textWord;
            }
        }
    }

    public boolean equals(Object o){
        Word thisWord = Word.createWord(this.textWord);
        if(thisWord.getText().toLowerCase().equals(((Word) o).getText().toLowerCase())){
            return true;
        }else return  false;
    }

    public String toString(){
        return this.textWord;
    }

    public static Word createWord(String rawText){
        Word newWord = new Word();
        newWord.textWord = rawText;
        return newWord;
    }


    public static boolean loadStopWords(String fileName) throws FileNotFoundException {
        String url = fileName;
        try {
            stopWords=new HashSet<>();
            FileInputStream fileInputStream = new FileInputStream(url);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                stopWords.add(scanner.nextLine());
            }
            scanner.close();
            fileInputStream.close();
        }catch(Exception e){
            return false;
        }
        return true;
    }


}


