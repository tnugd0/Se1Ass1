package engine;

import java.io.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

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


    public void testprefix(String rawText) {
        // Check valid of text part

        String text = "";
        String suffix = "";


        for (int i = 0; i < rawText.length(); i++) {
//            if ((Character.isLetterOrDigit(rawText.charAt(i)) || rawText.charAt(i) == '-' || rawText.charAt(i) == '\'')) {
////                if (Character.toString(rawText.charAt(i)) == "s")
//                text += rawText.charAt(i);
//            }


            if (!Character.isLetterOrDigit(rawText.charAt(i))) {
                if (rawText.charAt(i) == '-') {
                    text += rawText.charAt(i);
                }
                if (rawText.charAt(i) == '\'') {
                    if (rawText.charAt(i+1) == 's') {
                        suffix += Character.toString(rawText.charAt(i)) + rawText.charAt(i+1);

                        for (int j = i+1; j < rawText.length() ; j++) {
                            if (!Character.isLetterOrDigit(rawText.charAt(j))) {
                                suffix += rawText.charAt(j);
                            }
                        }
                    }
                    if (rawText.charAt(i+1) == 't'){
                        text += Character.toString(rawText.charAt(i)) + rawText.charAt(i+1);
                    }
                }
            }

            if (Character.isLetter(rawText.charAt(i))) {
                if (suffix.length() == 0 ) {
                    text += rawText.charAt(i);

                }
            }
        }
        System.out.println(!Character.isLetterOrDigit(rawText.charAt(rawText.length() - 1)));
        System.out.println(text);
        System.out.println(suffix);

    }
    public static Word createWord(String rawText) {
        String prefix = "";
        String suffix = "";
        String text = "";
        int index = 0;
        for (int i = 0 ;i < rawText.length(); i++) {
            if (!Character.isLetterOrDigit(rawText.charAt(i))) {
                prefix += rawText.charAt(i);
            }
        }
        if (prefix.length() != 0) {
            index = prefix.length() - 1;
        }
        for (int i = index; i < rawText.length() ; i++) {
            if (Character.isDigit(rawText.charAt(i))) {
                return new Word(prefix,rawText,suffix);
            }



            if (rawText.charAt(i) == '\'') {
                if (rawText.charAt(i+1) == 's') {
                    suffix += Character.toString(rawText.charAt(i)) + rawText.charAt(i+1);

                    for (int j = i+1; j < rawText.length() ; j++) {
                        if (!Character.isLetterOrDigit(rawText.charAt(j))) {
                            suffix += rawText.charAt(j);
                        }
                    }
                }
                if (rawText.charAt(i+1) == 't'){
                    text += Character.toString(rawText.charAt(i)) + rawText.charAt(i+1);
                }
            }


            if (Character.isLetter(rawText.charAt(i))) {
                text += rawText.charAt(i);

            }


        }





        // check valid word of valid word
        return null;

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
