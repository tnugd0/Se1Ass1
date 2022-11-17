package engine;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Word {
    public static Set<String> stopWords = new HashSet<>();

    public String prefix;
    public String suffix;
    public  String text;
    public boolean validword = true;

    public Word(String prefix, String suffix, String text) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.text = text;
    }

    public Word(String text) {
        this.text = text;
        this.validword = false;
        this.suffix = "";
        this.prefix = "";

    }

    public Word() {
        this.text = "";
    }

    public static Word createWordd(String rawText) {
        int index = 0;
        // find prefix
        while (index < rawText.length() && !Character.isLetterOrDigit(rawText.charAt(index))) {
            index++;
        }
        if (index == rawText.length())  return new Word (rawText);

        String prefix = rawText.substring(0, index);
        String text;
        String suffix;
        for (int i = index; i < rawText.length(); i++) {
            char c = rawText.charAt(i);
            if (Character.isDigit(c)) return new Word(rawText);
            if (c == '\'') {
                if (rawText.charAt(i+1) == 't') continue;
                else return new Word(prefix,rawText.substring(index,i),rawText.substring(i));
            }

            if (!Character.isLetter(c) && c != '-') {
                for (int j = i; j < rawText.length(); j++) {
                    if (Character.isLetter(rawText.charAt(j))) return new Word( rawText);
                }
                text = rawText.substring(index, i);
                suffix = rawText.substring(i);
                return new Word(prefix, text, suffix);
            }
        }
        text = rawText.substring(index);
        return new Word(prefix, text, "");
    }

    public boolean isKeyword() {

        if (validword) {
            return true;
        }
        return  false;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;

    }

    public String getText() {

        return this.text;
    }


    public boolean equals(Object o) {

        if (this.text.equalsIgnoreCase(o.toString())) {
            return true;
        }
        return false;


    }


    public boolean checkValid(String text) {

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                text += text.charAt(i);
            }




        }
        return true;
    }

    public void test(String rawText) {
        String prefix = "";
        String text = "";
        String suffix = "";


        int index = 0;
        for (int i = 0 ;i < rawText.length(); i++) {
            if (!Character.isLetterOrDigit(rawText.charAt(i))) {
                prefix += rawText.charAt(i);
            }
            if (Character.isLetterOrDigit(rawText.charAt(i))) {
                break;
            }
        }

        if (prefix.length() != 0 ) {
            if (prefix.length() != 1) {
                index = prefix.length() -1;
            }
            else {
                index = prefix.length();

            }
        }
        for (int i = index; i < rawText.length(); i++) {
            if (Character.isDigit(rawText.charAt(i))) {

//                return null;
                break;
            }
            if (rawText.charAt(i) == '\'') {
                if (rawText.charAt(i +1) == 's') {
                    continue;
                }

                else {
                    text = rawText.substring(index,i+2);
                    suffix = rawText.substring(i+2);
//                    return new Word(prefix,text,suffix);
                    break;
                }
            }

            if (rawText.charAt(i) == '-') {
//                text = rawText.substring(index)
                if (!Character.isLetter(rawText.charAt(i+1))) {
                    text = rawText.substring(index, i +1);
                    suffix = rawText.substring(i+1);

                }
                else {
                    text = rawText.substring(index);
                }

            }

            if (!Character.isLetter(rawText.charAt(i))) {

            }

        }

        System.out.println("prefix " + prefix);
        System.out.println("text " + text);
        System.out.println("suffix " + suffix);

    }


    public void testprefix(String rawTextt) {
        int startIndex = 0;

        String prefixx = "";
        String suffixx = "";
        String textt = "";
        int index = 0;
        for (int i = 0 ;i < rawTextt.length(); i++) {
            if (!Character.isLetterOrDigit(rawTextt.charAt(i))) {
                prefixx += rawTextt.charAt(i);
                index ++;
            }
            if (Character.isLetterOrDigit(rawTextt.charAt(i))) {
//
                break;
            }
        }
//        if (prefixx.length() != 0) {
//            index = prefixx.length() - 1;
//        }
        for (int i = index; i < rawTextt.length() ; i++) {
            if (Character.isDigit(rawTextt.charAt(i))) {
                break;
            }
            if (!Character.isLetterOrDigit(rawTextt.charAt(i))) {
                if (rawTextt.charAt(i) == '\'') {

                    if (rawTextt.charAt(i+1) == 's') {
//                        suffixx += Character.toString(rawTextt.charAt(i)) + rawTextt.charAt(i+1);
//                        startIndex =1;
//
//                        for (int j = i+1; j < rawTextt.length() ; j++) {
//                            if (!Character.isLetterOrDigit(rawTextt.charAt(j))) {
//                                suffixx += rawTextt.charAt(j);
//                            }
//                        }

                        textt = rawTextt.substring(index, i);
                        suffixx = rawTextt.substring(i,rawTextt.length() -1);

                    }
                    if (rawTextt.charAt(i+1) != 's'){
                        ArrayList<Character> list = new ArrayList<>();
                        list.add(rawTextt.charAt(i));
                        for (int j = i+1; j < rawTextt.length() ; j++) {
                            if (Character.isLetter(rawTextt.charAt(j)) && rawTextt.charAt(j) != 's') {
                                list.add(rawTextt.charAt(j));
                                System.out.println(34);
                            }
                        }
                        System.out.println(list.size());

                        textt  = rawTextt.substring(index,i);
                        for (Character c: list) {
                            textt += c;
                        }
                    }
                }  if (rawTextt.charAt(i) == '-') {
                    textt += rawTextt.charAt(i);
                }

//                else {
//                    suffixx += rawTextt.charAt(i);
//                }
            }

//             if(  startIndex != 1 && !Character.isLetterOrDigit(rawTextt.charAt(i))) {
//                suffixx += rawTextt.charAt(i);
//
//
//            }
//             if(Character.isLetter(rawTextt.charAt(i)) && startIndex != 1) {
//                textt += rawTextt.charAt(i);
//
//            }




        }
        // ceck valid word of valid word
        System.out.println("prefix: " +  prefixx);
        System.out.println("Text: " + textt);
        System.out.println("suffix: " +  suffixx);

    }
    public static Word createWord(String rawText) {
        String prefix = "";
        String text = "";
        String suffix = "";


        int index = 0;
        for (int i = 0 ;i < rawText.length(); i++) {
            if (!Character.isLetterOrDigit(rawText.charAt(i))) {
                prefix += rawText.charAt(i);
            }
            if (Character.isLetterOrDigit(rawText.charAt(i))) {
                break;
            }
        }

        if (prefix.length() != 0 ) {
            if (prefix.length() != 1) {
                index = prefix.length() -1;
            }
            else {
                index = prefix.length();

            }
        }
        for (int i = index; i < rawText.length(); i++) {
            if (Character.isDigit(rawText.charAt(i))) {

                return null;
            }
            if (rawText.charAt(i) == '\'') {
                if (rawText.charAt(i +1) == 's') {
                    continue;
                }

                else {
                    text = rawText.substring(index,i);
                    suffix = rawText.substring(i);
                    return new Word(prefix,text,suffix);
                }
            }

            if (rawText.charAt(i) == '-') {
//                text = rawText.substring(index)
                if (!Character.isLetter(rawText.charAt(i+1))) {
                    text = rawText.substring(index, i +1);
                    suffix = rawText.substring(i+1);

                }
                else {
                    text = rawText.substring(index);
                }

            }

        }
//        text = rawText.substring(index);
        return new Word(prefix, text, suffix);

    }

    public static boolean loadStopWords(String fileName) {
        try {

            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String stopword = new String(data, "UTF-8");
            stopWords.add(stopword);
            return true;

        } catch (IOException e) {
            return false;
        }
    }




    @Override
    public String toString() {
        return "Word{" +
                "prefix='" + prefix + '\'' +
                ", suffix='" + suffix + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
