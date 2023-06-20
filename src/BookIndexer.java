import java.io.*;
import java.util.*;
public class BookIndexer {
    private Set<String> excludeWords;
    private Map<String, Set<Integer>> index;

    public BookIndexer() {
        excludeWords = new HashSet<>();
        index = new TreeMap<>();
    }

    public void loadExcludeWords(String excludeWordsFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(excludeWordsFile))) {
            String word;
            while ((word = reader.readLine()) != null) {
                excludeWords.add(word.trim());
            }
        }
    }
    private List<String> extractWords(String pageFile) throws IOException {
        List<String> words = new ArrayList<>();
        File file = new File(pageFile);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                // Remove any punctuation marks or special characters
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                // Add the word to the list
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }

        return words;
    }


    public void createIndex(String[] pageFiles) throws IOException {
//        for (int i = 0; i < pageFiles.length; i++) {
//            String pageFile = pageFiles[i];
//            try (BufferedReader reader = new BufferedReader(new FileReader(pageFile))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] words = line.split("\\s+");
//                    for (String word : words) {
//                        word = word.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
//                        if (!excludeWords.contains(word)) {
//                            Set<Integer> pages = index.getOrDefault(word, new HashSet<>());
//                            pages.add(i + 1);
//                            index.put(word, pages);
        for (int i = 0; i < pageFiles.length; i++) {
            String pageFile = pageFiles[i];
            List<String> words = extractWords(pageFile);

            // Remove duplicate page numbers for each word
            Set<Integer> uniquePageNumbers = new HashSet<>();
            uniquePageNumbers.add(i + 1);

            // Process each word
            for (String word : words) {
                // Exclude common words
                if (!excludeWords.contains(word.toLowerCase())) {
                    // Add unique page numbers to word index
                    if (!index.containsKey(word)) {
                        index.put(word, new HashSet<>());
                    }
                    index.get(word).addAll(uniquePageNumbers);

                        }
                    }
                }
            }

    public void writeIndex(String indexFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(indexFile))) {
            for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
                String word = entry.getKey();
                Set<Integer> pages = entry.getValue();
                writer.write(word + " : " + pagesToString(pages));
                writer.newLine();
            }
        }
    }

    private String pagesToString(Set<Integer> pages) {
        StringBuilder sb = new StringBuilder();
        for (int page : pages) {
            sb.append(page).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}




