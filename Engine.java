package a1_2001040219;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Engine {

    private Doc[] docs;
    public int loadDocs(String dirname) {
        File folder = new File(dirname);
        File[] file = folder.listFiles();
        this.docs = new Doc[file.length];
        for (int i = 0; i < file.length; i++) {
            String textContent = null;
            try {
                textContent = Files.readString(file[i].getAbsoluteFile().toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.docs[i] = new Doc(textContent);
        }

        return file.length;
    }

    public Doc[] getDocs() {
        return this.docs;
    }

    public List<Result> search(Query q) {
        List<Result> results = new LinkedList<>();
        for (int i = 0; i < this.docs.length; i++) {
            List<Match> matches = q.matchAgainst(this.docs[i]);
            if (matches.size() > 0) {
                results.add(new Result(this.docs[i],q.matchAgainst(this.docs[i])));
            }
        }
        List<Result> result =  results.stream().sorted().collect(Collectors.toList());
        return result;
    }
    public String htmlResult(List<Result> results) {
        StringBuilder returnBody = new StringBuilder();

        for (Result result: results) {
            returnBody.append(result.htmlHighlight());
        }
        return returnBody.toString();
    }



}
