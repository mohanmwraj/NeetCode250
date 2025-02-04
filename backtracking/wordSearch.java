package backtracking;


import java.util.HashSet;
import java.util.Set;

class Pair<First, Second> {
    private First first;
    private Second second;

    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public void setSecond(Second second) {
        this.second = second;
    }

    public First getKey() {
        return first;
    }

    public Second getValue() {
        return second;
    }

    public void set(First first, Second second) {
        setFirst(first);
        setSecond(second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;
        if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
        if (second != null ? !second.equals(pair.second) : pair.second != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}

public class wordSearch {
    /*
        * Approach: Backtracking (Hash Set)
        *
     */
    private int ROWS, COLS;
    Set<Pair<Integer, Integer>> path = new HashSet<>();

    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLS = board[0].length;

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int i) {
        if (i == word.length()) {
            return true;
        }

        if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
                board[r][c] != word.charAt(i) ||
                path.contains(new Pair<>(r, c))) {
            return false;
        }

        path.add(new Pair<>(r, c));
        boolean res = dfs(board, word, r + 1, c, i + 1) ||
                dfs(board, word, r - 1, c, i + 1) ||
                dfs(board, word, r, c + 1, i + 1) ||
                dfs(board, word, r, c - 1, i + 1);
        path.remove(new Pair<>(r, c));

        return res;
    }
    /*
        Time Complexity: O(m * 4^n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Backtracking Visited Array
        *
     */
    public class Solution_2 {
        private int ROWS, COLS;
        private boolean[][] visited;

        public boolean exist(char[][] board, String word) {
            ROWS = board.length;
            COLS = board[0].length;
            visited = new boolean[ROWS][COLS];

            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int r, int c, int i) {
            if (i == word.length()) {
                return true;
            }

            if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
                    board[r][c] != word.charAt(i) || visited[r][c]) {
                return false;
            }

            visited[r][c] = true;
            boolean res = dfs(board, word, r + 1, c, i + 1) ||
                    dfs(board, word, r - 1, c, i + 1) ||
                    dfs(board, word, r, c + 1, i + 1) ||
                    dfs(board, word, r, c - 1, i + 1);
            visited[r][c] = false;

            return res;
        }
    }
    /*
        Time Complexity: O(m * 4^n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Backtracking Optimal
        *
     */
    public class Solution_3 {
        private int ROWS, COLS;

        public boolean exist(char[][] board, String word) {
            ROWS = board.length;
            COLS = board[0].length;

            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int r, int c, int i) {
            if (i == word.length()) {
                return true;
            }
            if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
                    board[r][c] != word.charAt(i) || board[r][c] == '#') {
                return false;
            }

            board[r][c] = '#';
            boolean res = dfs(board, word, r + 1, c, i + 1) ||
                    dfs(board, word, r - 1, c, i + 1) ||
                    dfs(board, word, r, c + 1, i + 1) ||
                    dfs(board, word, r, c - 1, i + 1);
            board[r][c] = word.charAt(i);
            return res;
        }
    }
    /*
        Time Complexity: O(m * 4^n)
        Space Complexity: O(n)
     */
}
