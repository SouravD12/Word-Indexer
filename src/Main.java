//import java.io.IOException;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        String excludeWordsFile = "exclude-words.txt";
        String[] pageFiles = {"Page1.txt", "Page2.txt", "Page3.txt"};
        String indexFile = "index.txt";

        BookIndexer indexer = new BookIndexer();
        try {
            indexer.loadExcludeWords(excludeWordsFile);
            indexer.createIndex(pageFiles);
            indexer.writeIndex(indexFile);
            System.out.println("Index created successfully!");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}