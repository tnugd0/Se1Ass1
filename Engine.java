package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine {

    private String dirname;
    public Engine(){
        this.dirname = null;
    }
    public int loadDocs(String dirname) {
        File folder = new File(dirname);
        File[] files = folder.listFiles();
        this.dirname = dirname;
        return files.length;
    }

    public Doc[] getDocs() throws FileNotFoundException {
        File folder = new File(this.dirname);
        File[] files = folder.listFiles();
        Doc[] docsList = new Doc[files.length];
        for(int i =0;i<files.length;i++){
            String content ="";
            FileInputStream fileInputStream = new FileInputStream(files[i].getAbsolutePath());
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                content = content + scanner.nextLine()+"\n";
            }
            Doc newDoc = new Doc(content);
            docsList[i] = newDoc;
        }
        return docsList;

    }

    public List<Result> search(Query q) throws FileNotFoundException {
        List<Result> results = new ArrayList<Result>();
        Doc[] listDocs = this.getDocs();
        for(Doc doc : listDocs){
            List<Match> matches = q.matchAgainst(doc);

            if(matches.size()>0){
                Result newResult = new Result(doc, matches);
                results.add(newResult);
            }

        }
        Collections.sort(results);
        return results;

    }
    public String htmlResult(List<Result> results) {
        String returnBody = "";

        for (Result result: results) {
            returnBody += result.htmlHighLight();
        }


        return returnBody;
    }


}
