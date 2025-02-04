package backtracking;

import java.util.ArrayList;
import java.util.List;

public class palindromPartitioning {
    /*
        * Approach: Backtracking - Pick/Not Pick
        *
     */
    private List<List<String>> res = new ArrayList<>();
    private List<String> part = new ArrayList<>();

    public List<List<String>> partition_1(String s) {
        dfs(0, 0, s);
        return res;
    }

    private void dfs(int j, int i, String s) {
        if (i >= s.length()) {
            if (i == j) {
                res.add(new ArrayList<>(part));
            }
            return;
        }

        if (isPali(s, j, i)) {
            part.add(s.substring(j, i + 1));
            dfs(i + 1, i + 1, s);
            part.remove(part.size() - 1);
        }

        dfs(j, i + 1, s);
    }

    private boolean isPali(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Backtracking
        *
     */
    public List<List<String>> partition_2(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> part = new ArrayList<>();
        dfs(0, s, part, res);
        return res;
    }

    private void dfs(int i, String s, List<String> part, List<List<String>> res) {
        if (i >= s.length()) {
            res.add(new ArrayList<>(part));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (isPali(s, i, j)) {
                part.add(s.substring(i, j + 1));
                dfs(j + 1, s, part, res);
                part.remove(part.size() - 1);
            }
        }
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Backtracking DP
        *
     */
    boolean[][] dp;
    public List<List<String>> partition_3(String s) {
        int n = s.length();
        dp = new boolean[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                dp[i][i + l - 1] = (s.charAt(i) == s.charAt(i + l - 1) &&
                        (i + 1 > (i + l - 2) ||
                                dp[i + 1][i + l - 2]));
            }
        }

        List<List<String>> res = new ArrayList<>();
        List<String> part = new ArrayList<>();
        dfs_3(0, s, part, res);
        return res;
    }

    private void dfs_3(int i, String s, List<String> part, List<List<String>> res) {
        if (i >= s.length()) {
            res.add(new ArrayList<>(part));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (dp[i][j]) {
                part.add(s.substring(i, j + 1));
                dfs_3(j + 1, s, part, res);
                part.remove(part.size() - 1);
            }
        }
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n^2)
     */
    /*
        * Approach: Recursion
        *
     */
    public List<List<String>> partition_4(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                dp[i][i + l - 1] = (s.charAt(i) == s.charAt(i + l - 1) &&
                        (i + 1 > (i + l - 2) ||
                                dp[i + 1][i + l - 2]));
            }
        }

        return dfs_4(s, dp, 0);
    }

    private List<List<String>> dfs_4(String s, boolean[][] dp, int i) {
        if (i >= s.length()) {
            return new ArrayList<List<String>>() {{ add(new ArrayList<>()); }};
        }

        List<List<String>> ret = new ArrayList<>();
        for (int j = i; j < s.length(); j++) {
            if (dp[i][j]) {
                List<List<String>> nxt = dfs_4(s, dp, j + 1);
                for (List<String> part : nxt) {
                    List<String> cur = new ArrayList<>();
                    cur.add(s.substring(i, j + 1));
                    cur.addAll(part);
                    ret.add(cur);
                }
            }
        }
        return ret;
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n^2)
     */
}
