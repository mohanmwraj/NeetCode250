package tries;

import java.util.HashMap;

public class implementTriePrefixTree {
    /*
        * Approach: Prefix Tree Array
        *
     */
    class Solution_1{
        public class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean endOfWord = false;
        }

        public class PrefixTree {
            private TrieNode root;

            public PrefixTree() {
                root = new TrieNode();
            }

            public void insert(String word) {
                TrieNode cur = root;
                for (char c : word.toCharArray()) {
                    int i = c - 'a';
                    if (cur.children[i] == null) {
                        cur.children[i] = new TrieNode();
                    }
                    cur = cur.children[i];
                }
                cur.endOfWord = true;
            }

            public boolean search(String word) {
                TrieNode cur = root;
                for (char c : word.toCharArray()) {
                    int i = c - 'a';
                    if (cur.children[i] == null) {
                        return false;
                    }
                    cur = cur.children[i];
                }
                return cur.endOfWord;
            }

            public boolean startsWith(String prefix) {
                TrieNode cur = root;
                for (char c : prefix.toCharArray()) {
                    int i = c - 'a';
                    if (cur.children[i] == null) {
                        return false;
                    }
                    cur = cur.children[i];
                }
                return true;
            }
        }
    }
    /*
        Time Complexity: O(n) for each function call.
        Space Complexity: O(t)
     */
    /*
        * Approach: Prefix Tree(Hash Map)
        *
     */
    class Solution_2{
        public class TrieNode {
            HashMap<Character, TrieNode> children = new HashMap<>();
            boolean endOfWord = false;
        }

        public class PrefixTree {
            private TrieNode root;

            public PrefixTree() {
                root = new TrieNode();
            }

            public void insert(String word) {
                TrieNode cur = root;
                for (char c : word.toCharArray()) {
                    cur.children.putIfAbsent(c, new TrieNode());
                    cur = cur.children.get(c);
                }
                cur.endOfWord = true;
            }

            public boolean search(String word) {
                TrieNode cur = root;
                for (char c : word.toCharArray()) {
                    if (!cur.children.containsKey(c)) {
                        return false;
                    }
                    cur = cur.children.get(c);
                }
                return cur.endOfWord;
            }

            public boolean startsWith(String prefix) {
                TrieNode cur = root;
                for (char c : prefix.toCharArray()) {
                    if (!cur.children.containsKey(c)) {
                        return false;
                    }
                    cur = cur.children.get(c);
                }
                return true;
            }
        }
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(t)
     */
}
