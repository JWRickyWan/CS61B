

import edu.princeton.cs.algs4.In;

import java.util.List;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        In words = new In(dictPath);
        String[] all_words = words.readAllLines();
        Trie wordTrie = new Trie();
        for(String word:all_words){
            wordTrie.put(word);
        }


    }
}
