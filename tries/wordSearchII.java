package tries;

import java.util.*;

public class wordSearchII {
    /*
        * Approach: Backtracking
        *
     */
    public List<String> findWords_1(char[][] board, String[] words) {
        int ROWS = board.length, COLS = board[0].length;
        List<String> res = new ArrayList<>();

        for (String word : words) {
            boolean flag = false;
            for (int r = 0; r < ROWS && !flag; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (board[r][c] != word.charAt(0)) continue;
                    if (backtrack(board, r, c, word, 0)) {
                        res.add(word);
                        flag = true;
                        break;
                    }
                }
            }
        }
        return res;
    }

    private boolean backtrack(char[][] board, int r, int c, String word, int i) {
        if (i == word.length()) return true;
        if (r < 0 || c < 0 || r >= board.length ||
                c >= board[0].length || board[r][c] != word.charAt(i))
            return false;

        board[r][c] = '*';
        boolean ret = backtrack(board, r + 1, c, word, i + 1) ||
                backtrack(board, r - 1, c, word, i + 1) ||
                backtrack(board, r, c + 1, word, i + 1) ||
                backtrack(board, r, c - 1, word, i + 1);
        board[r][c] = word.charAt(i);
        return ret;
    }
    /*
        Time Complexity: O(m * n * 4^t + s)
        Space Complexity: O(t)
     */

    /*
        * Approach: Backtracking Trie + Hash Set
        *
     */
    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }

        public void addWord(String word) {
            TrieNode cur = this;
            for (char c : word.toCharArray()) {
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }
    }

    public class Solution_1 {
        private Set<String> res;
        private boolean[][] visit;

        public List<String> findWords(char[][] board, String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                root.addWord(word);
            }

            int ROWS = board.length, COLS = board[0].length;
            res = new HashSet<>();
            visit = new boolean[ROWS][COLS];

            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    dfs(board, r, c, root, "");
                }
            }
            return new ArrayList<>(res);
        }

        private void dfs(char[][] board, int r, int c, TrieNode node, String word) {
            int ROWS = board.length, COLS = board[0].length;
            if (r < 0 || c < 0 || r >= ROWS ||
                    c >= COLS || visit[r][c] ||
                    !node.children.containsKey(board[r][c])) {
                return;
            }

            visit[r][c] = true;
            node = node.children.get(board[r][c]);
            word += board[r][c];
            if (node.isWord) {
                res.add(word);
            }

            dfs(board, r + 1, c, node, word);
            dfs(board, r - 1, c, node, word);
            dfs(board, r, c + 1, node, word);
            dfs(board, r, c - 1, node, word);

            visit[r][c] = false;
        }
    }
    /*
        Time Complexity: O(m * n * 4 * 3^(t - 1) + s)
        Space Complexity: O(s)
     */
    /*
        * Approach: Backtracking Trie
        *
     */
    public class TrieNode_3 {
        TrieNode_3[] children = new TrieNode_3[26];
        int idx = -1;
        int refs = 0;

        public void addWord(String word, int i) {
            TrieNode_3 cur = this;
            cur.refs++;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new TrieNode_3();
                }
                cur = cur.children[index];
                cur.refs++;
            }
            cur.idx = i;
        }
    }

    public class Solution {
        List<String> res = new ArrayList<>();

        public List<String> findWords(char[][] board, String[] words) {
            TrieNode_3 root = new TrieNode_3();
            for (int i = 0; i < words.length; i++) {
                root.addWord(words[i], i);
            }

            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    dfs(board, root, r, c, words);
                }
            }

            return res;
        }

        private void dfs(char[][] board, TrieNode_3 node, int r, int c, String[] words) {
            if (r < 0 || c < 0 || r >= board.length ||
                    c >= board[0].length || board[r][c] == '*' ||
                    node.children[board[r][c] - 'a'] == null) {
                return;
            }

            char temp = board[r][c];
            board[r][c] = '*';
            TrieNode_3 prev = node;
            node = node.children[temp - 'a'];
            if (node.idx != -1) {
                res.add(words[node.idx]);
                node.idx = -1;
                node.refs--;
                if (node.refs == 0) {
                    node = null;
                    prev.children[temp - 'a'] = null;
                    board[r][c] = temp;
                    return;
                }
            }

            dfs(board, node, r + 1, c, words);
            dfs(board, node, r - 1, c, words);
            dfs(board, node, r, c + 1, words);
            dfs(board, node, r, c - 1, words);

            board[r][c] = temp;
        }
    }
    /*
        Time Complexity: O(m * n * 4 * 3^(t - 1) + s)
        Space Complexity: O(s)
     */

}
