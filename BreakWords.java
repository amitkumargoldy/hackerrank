import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private Map<String, List<List<String>>> dp = new HashMap<>();

    public List<List<String>> breakAllWords(List<String> words) {
        trie = new Trie();
        if (words != null && !words.isEmpty()) {
            for (String word : words)
                trie.add(word);

            for (String word : words) {
                List<List<String>> brokenWord = breakWord(word, 0);
                brokenWordList.addAll(brokenWord.stream().filter(e -> e.size() > 1).collect(Collectors.toList()));
            }
        }
        return brokenWordList;
    }

    public List<List<String>> breakWord(String word, int idx) {
        if (dp.get(word.substring(idx)) != null)
            return dp.get(word.substring(idx));
        List<List<String>> brokenWord = new ArrayList<>();
        Trie.Node node = trie.root;
        for (int i = idx; i < word.length(); i++) {
            node = node.children[word.charAt(i) - 'a'];
            if (node == null)
                break;
            if (node.endOfWord) {
                if (i == word.length() - 1) {
                    brokenWord.add(List.of(word.substring(idx)));
                } else {
                    String remainingWord = word.substring(i + 1);
                    dp.put(remainingWord, breakWord(word, i + 1));
                    for (List<String> e : dp.get(remainingWord)) {
                        List<String> combi = new ArrayList<>();
                        combi.add(word.substring(idx, i + 1));
                        combi.addAll(e);
                        brokenWord.add(combi);
                    }
                }
            }

        }
        return brokenWord;
    }

    public static void main(String[] args) {
        List<String> words = List.of(
                "harrypotter",
                "cat",
                "dog",
                "harry",
                "wall",
                "potter",
                "aliceinwonderland",
                "alice",
                "alicein",
                "in",
                "wonderland",
                "wondqw",
                "land",
                "wonder"
        );

        BreakWords breakWords = new BreakWords();
        System.out.println(breakWords.breakAllWords(words));
    }
}
