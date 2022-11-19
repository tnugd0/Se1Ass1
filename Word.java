package engine;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
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



    public static Word createWord(String rawText) {
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

        if (this.text.length() == 0) {
            return false;
        }

        for (String words: stopWords) {
            if (words == this.text) {
                return true;
            }
        }

        return true;
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

//        if (this.text.equalsIgnoreCase(o.toString())) {
//            return true;
//        }
//        return false;

        if (this == o) {
            return true;
        }

        if (!(o instanceof Word w )) {
            return false;
        }
        return this.text.equalsIgnoreCase(w.text);

    }
    public void test(String rawText) {
        String prefix = "";
        String text = "";
        String suffix = "";


        int index = 0;
        for (int i = 0 ;i < rawText.length(); i++) {
            if (Character.isDigit(rawText.charAt(i))) {
                break;
            }
            if (!Character.isLetter(rawText.charAt(i))) {
                prefix += rawText.charAt(i);
                index ++;
            }
        }

        if (index == rawText.length()) {
            System.out.println("Invalid");
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

                System.out.println(index);
                text = rawText.substring(index,i+2);
                suffix = rawText.substring(i+2);
//              return new Word(prefix,text,suffix);
                break;

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



//    public static Word createWord(String rawText) {
//        String prefix = "";
//        String text = "";
//        String suffix = "";
//
//
//        int index = 0;
//        for (int i = 0 ;i < rawText.length(); i++) {
//            if (!Character.isLetterOrDigit(rawText.charAt(i))) {
//                prefix += rawText.charAt(i);
//            }
//            if (Character.isLetterOrDigit(rawText.charAt(i))) {
//                break;
//            }
//        }
//
//        if (prefix.length() != 0 ) {
//            if (prefix.length() != 1) {
//                index = prefix.length() -1;
//            }
//            else {
//                index = prefix.length();
//
//            }
//        }
//        for (int i = index; i < rawText.length(); i++) {
//            if (Character.isDigit(rawText.charAt(i))) {
//
//                return null;
//            }
//            if (rawText.charAt(i) == '\'') {
//                if (rawText.charAt(i +1) == 's') {
//                    continue;
//                }
//
//                else {
//                    text = rawText.substring(index,i);
//                    suffix = rawText.substring(i);
//                    return new Word(prefix,text,suffix);
//                }
//            }
//
//            if (rawText.charAt(i) == '-') {
////                text = rawText.substring(index)
//                if (!Character.isLetter(rawText.charAt(i+1))) {
//                    text = rawText.substring(index, i +1);
//                    suffix = rawText.substring(i+1);
//
//                }
//                else {
//                    text = rawText.substring(index);
//                }
//
//            }
//
//        }
//        text = rawText.substring(index);
//        return new Word(prefix, text, suffix);

//    }

    public static boolean loadStopWords(String fileName) {
        try {

            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String stopword = new String(data, StandardCharsets.UTF_8);
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
