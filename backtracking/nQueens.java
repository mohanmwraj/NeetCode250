package backtracking;

import java.util.*;

public class nQueens {
    /*
        * Approach: Backtracking
        *
     */
    public List<List<String>> solveNQueens_1(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(0, board, res);
        return res;
    }

    private void backtrack(int r, char[][] board, List<List<String>> res) {
        if (r == board.length) {
            List<String> copy = new ArrayList<>();
            for (char[] row : board) {
                copy.add(new String(row));
            }
            res.add(copy);
            return;
        }
        for (int c = 0; c < board.length; c++) {
            if (isSafe(r, c, board)) {
                board[r][c] = 'Q';
                backtrack(r + 1, board, res);
                board[r][c] = '.';
            }
        }
    }

    private boolean isSafe(int r, int c, char[][] board) {
        for (int i = r - 1; i >= 0; i--) {
            if (board[i][c] == 'Q') return false;
        }
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = r - 1, j = c + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }
    /*
        Time Complexity: O(n!)
        Space Complexity: O(n^2)
     */
    /*
        * Approach: Backtracking Hash Set
        *
     */
    Set<Integer> col = new HashSet<>();
    Set<Integer> posDiag = new HashSet<>();
    Set<Integer> negDiag = new HashSet<>();
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens_2(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack(0, n, board);
        return res;
    }

    private void backtrack(int r, int n, char[][] board) {
        if (r == n) {
            List<String> copy = new ArrayList<>();
            for (char[] row : board) {
                copy.add(new String(row));
            }
            res.add(copy);
            return;
        }

        for (int c = 0; c < n; c++) {
            if (col.contains(c) || posDiag.contains(r + c)
                    || negDiag.contains(r - c)) {
                continue;
            }

            col.add(c);
            posDiag.add(r + c);
            negDiag.add(r - c);
            board[r][c] = 'Q';

            backtrack(r + 1, n, board);

            col.remove(c);
            posDiag.remove(r + c);
            negDiag.remove(r - c);
            board[r][c] = '.';
        }
    }
    /*
        Time Complexity: O(n!)
        Space Complexity: O(n^2)
     */
    /*
        * Approach: Backtracking (Visited Array)
        *
     */
    public class Solution_3 {
        boolean[] col, posDiag, negDiag;
        List<List<String>> res;
        char[][] board;

        public List<List<String>> solveNQueens_3(int n) {
            col = new boolean[n];
            posDiag = new boolean[2 * n];
            negDiag = new boolean[2 * n];
            res = new ArrayList<>();
            board = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }
            backtrack(0, n);
            return res;
        }

        private void backtrack(int r, int n) {
            if (r == n) {
                List<String> copy = new ArrayList<>();
                for (char[] row : board) {
                    copy.add(new String(row));
                }
                res.add(copy);
                return;
            }
            for (int c = 0; c < n; c++) {
                if (col[c] || posDiag[r + c] || negDiag[r - c + n]) {
                    continue;
                }
                col[c] = true;
                posDiag[r + c] = true;
                negDiag[r - c + n] = true;
                board[r][c] = 'Q';

                backtrack(r + 1, n);

                col[c] = false;
                posDiag[r + c] = false;
                negDiag[r - c + n] = false;
                board[r][c] = '.';
            }
        }
    }
    /*
        Time Complexity: O(n!)
        Space Complexity: O(n^2)
     */
    /*
        * Approach: Bit Masking
        *
     */
    public class Solution_4 {
        int col = 0, posDiag = 0, negDiag = 0;
        List<List<String>> res;
        char[][] board;

        public List<List<String>> solveNQueens_4(int n) {
            res = new ArrayList<>();
            board = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }
            backtrack(0, n);
            return res;
        }

        private void backtrack(int r, int n) {
            if (r == n) {
                List<String> copy = new ArrayList<>();
                for (char[] row : board) {
                    copy.add(new String(row));
                }
                res.add(copy);
                return;
            }
            for (int c = 0; c < n; c++) {
                if ((col & (1 << c)) > 0 || (posDiag & (1 << (r + c))) > 0
                        || (negDiag & (1 << (r - c + n))) > 0) {
                    continue;
                }
                col ^= (1 << c);
                posDiag ^= (1 << (r + c));
                negDiag ^= (1 << (r - c + n));
                board[r][c] = 'Q';

                backtrack(r + 1, n);

                col ^= (1 << c);
                posDiag ^= (1 << (r + c));
                negDiag ^= (1 << (r - c + n));
                board[r][c] = '.';
            }
        }
    }
    /*
        Time Complexity: O(n!)
        Space Complexity: O(n^2)
     */
}
