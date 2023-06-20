import java.io.*;
import java.util.*;

public class BookIndexingProgram {
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
        String directoryPath = "."; // Replace with the directory path where your Java source files are located
        String testFileName = "test.txt";

        File testFile = new File(directoryPath, testFileName);
        try {
            boolean created = testFile.createNewFile();
            if (created) {
                System.out.println("Successfully created the test file.");
            } else {
                System.out.println("Failed to create the test file.");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating the test file: " + e.getMessage());
        }
    }
}
