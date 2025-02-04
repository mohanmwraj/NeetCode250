package backtracking;

import java.util.*;

public class combinationSumII {
    /*
        * Approach: Brute Force
        *
     */
    private Set<List<Integer>> res;

    public List<List<Integer>> combinationSum2_1(int[] candidates, int target) {
        res = new HashSet<>();
        Arrays.sort(candidates);
        generateSubsets_1(candidates, target, 0, new ArrayList<>(), 0);
        return new ArrayList<>(res);
    }

    private void generateSubsets_1(int[] candidates, int target, int i, List<Integer> cur, int total) {
        if (total == target) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (total > target || i == candidates.length) {
            return;
        }

        cur.add(candidates[i]);
        generateSubsets_1(candidates, target, i + 1, cur, total + candidates[i]);
        cur.remove(cur.size() - 1);

        generateSubsets_1(candidates, target, i + 1, cur, total);
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n * 2^n)
     */

    /*
        * Approach: Backtracking
        *
     */
    private List<List<Integer>> res1;

    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        res1 = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList<>(), 0);
        return res1;
    }

    private void dfs(int[] candidates, int target, int i, List<Integer> cur, int total) {
        if (total == target) {
            res1.add(new ArrayList<>(cur));
            return;
        }
        if (total > target || i == candidates.length) {
            return;
        }

        cur.add(candidates[i]);
        dfs(candidates, target, i + 1, cur, total + candidates[i]);
        cur.remove(cur.size() - 1);

        while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
            i++;
        }
        dfs(candidates, target, i + 1, cur, total);
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Backtracking Hash Map
        *
     */
    List<List<Integer>> res2 = new ArrayList<>();
    Map<Integer, Integer> count = new HashMap<>();

    public List<List<Integer>> combinationSum2_3(int[] nums, int target) {
        List<Integer> cur = new ArrayList<>();
        List<Integer> A = new ArrayList<>();

        for (int num : nums) {
            if (!count.containsKey(num)) {
                A.add(num);
            }
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        backtrack_3(A, target, cur, 0);
        return res2;
    }

    private void backtrack_3(List<Integer> nums, int target, List<Integer> cur, int i) {
        if (target == 0) {
            res2.add(new ArrayList<>(cur));
            return;
        }
        if (target < 0 || i >= nums.size()) {
            return;
        }

        if (count.get(nums.get(i)) > 0) {
            cur.add(nums.get(i));
            count.put(nums.get(i), count.get(nums.get(i)) - 1);
            backtrack_3(nums, target - nums.get(i), cur, i);
            count.put(nums.get(i), count.get(nums.get(i)) + 1);
            cur.remove(cur.size() - 1);
        }

        backtrack_3(nums, target, cur, i + 1);
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Backtracking Optimal
        *
     */
    private static List<List<Integer>> res3 = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res3.clear();
        Arrays.sort(candidates);
        dfs(0, new ArrayList<>(), 0, candidates, target);
        return res3;
    }

    private static void dfs(int idx, List<Integer> path, int cur, int[] candidates, int target) {
        if (cur == target) {
            res3.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (cur + candidates[i] > target) {
                break;
            }

            path.add(candidates[i]);
            dfs(i + 1, path, cur + candidates[i], candidates, target);
            path.remove(path.size() - 1);
        }
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n)
     */
}
