package backtracking;

import java.util.ArrayList;
import java.util.List;

public class subsets {
    /*
        * Approach: Backtracking
        *
     */
    public List<List<Integer>> subsets_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs_1(nums, 0, subset, res);
        return res;
    }

    private void dfs_1(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        subset.add(nums[i]);
        dfs_1(nums, i + 1, subset, res);
        subset.remove(subset.size() - 1);
        dfs_1(nums, i + 1, subset, res);
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iteration
        *
     */
    public List<List<Integer>> subsets_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(res.get(i));
                subset.add(num);
                res.add(subset);
            }
        }

        return res;
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Bit Manipulation
        *
     */
    public List<List<Integer>> subsets_3(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            res.add(subset);
        }
        return res;
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(n)
     */
}
