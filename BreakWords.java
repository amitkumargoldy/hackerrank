import java.util.ArrayList;
import java.util.List;

public class BreakWords {

    class Trie {
        class Node {
            Node[] children = new Node[26];
            boolean endOfWord;
        }

        Node root;

        public void add(String str) {
            if (root == null)
                root = new Node();
            Node node = root;
            for (char c : str.toCharArray()) {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new Node();
                node = node.children[c - 'a'];
            }
            node.endOfWord = true;
        }
    }

    private Trie trie;
    private List<List<String>> brokenWordList = new ArrayList<>();

    public List<List<String>> breakAllWords(List<String> words) {
        trie = new Trie();
        if (words != null && !words.isEmpty()) {
            for (String word : words)
                trie.add(word);

            for (String word : words)
                breakWord(word, 0);
        }
        return brokenWordList;
    }

    public List<List<String>> breakWord(String word, int idx) {
        List<List<String>> brokenWord = new ArrayList<>();
        Trie.Node node = trie.root;
        for (int i = idx; i < word.length(); i++) {
            node = node.children[word.charAt(i) - 'a'];
            if (node == null)
                break;
            if (node.endOfWord) {
                List<List<String>> breakRemaining;
                if (i < word.length() - 1) {
                    breakRemaining = breakWord(word, i + 1);
                } else {
                    breakRemaining = new ArrayList<>();
                    breakRemaining.add(List.of(word.substring(idx)));
                    return breakRemaining;
                }

                for (List<String> e : breakRemaining) {
                    List<String> combi = new ArrayList<>();
                    combi.add(word.substring(0, i + 1));
                    combi.addAll(e);
                    brokenWordList.add(combi);
                }
            }

        }
        return brokenWord;
    }

    public static void main(String[] args) {
        List<String> words = List.of(
                "harrypotter", "cat", "dog", "harry", "wall", "potter", "aliceinwonderland", "alice", "in",
                "wonderland", "land", "wonder"
        );

        BreakWords breakWords = new BreakWords();
        System.out.println(breakWords.breakAllWords(words));
    }
}
