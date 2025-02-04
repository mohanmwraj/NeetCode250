package tries;

import java.util.ArrayList;
import java.util.List;

public class designAddAndSearchWordsDS {
    /*
        * Approach: Brute Force
        *
     */
    public class WordDictionary_1 {

        private List<String> store;

        public WordDictionary_1() {
            store = new ArrayList<>();
        }

        public void addWord(String word) {
            store.add(word);
        }

        public boolean search(String word) {
            for (String w : store) {
                if (w.length() != word.length()) continue;
                int i = 0;
                while (i < w.length()) {
                    if (w.charAt(i) == word.charAt(i) ||
                            word.charAt(i) == '.') {
                        i++;
                    } else {
                        break;
                    }
                }
                if (i == w.length()) {
                    return true;
                }
            }
            return false;
        }
    }
    /*
        Time Complexity: O(1) for addWord(), O(m∗n) for search().
        Space complexity: O(m∗n)
     */
    /*
        * Approach: Depth First Search Trie
        *
     */
    public class TrieNode {

        TrieNode[] children;
        boolean word;

        public TrieNode() {
            children = new TrieNode[26];
            word = false;
        }
    }

    public class WordDictionary_2 {

        private TrieNode root;

        public WordDictionary_2() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.word = true;
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int j, TrieNode root) {
            TrieNode cur = root;

            for (int i = j; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c == '.') {
                    for (TrieNode child : cur.children) {
                        if (child != null && dfs(word, i + 1, child)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    if (cur.children[c - 'a'] == null) {
                        return false;
                    }
                    cur = cur.children[c - 'a'];
                }
            }
            return cur.word;
        }
    }
    /*
        Time Complexity: O(n) for addWord(), O(n) for search().
        Space complexity: O(t + n)
     */
}
