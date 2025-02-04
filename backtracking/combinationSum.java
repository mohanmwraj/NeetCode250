package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class combinationSum {
    /*
        * Approach: Backtracking
        *
     */
    List<List<Integer>> res;
    public List<List<Integer>> combinationSum_1(int[] nums, int target) {
        res = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList();
        backtrack_1(nums, target, cur, 0);
        return res;
    }

    public void backtrack_1(int[] nums, int target, List<Integer> cur, int i) {
        if (target == 0) {
            res.add(new ArrayList(cur));
            return;
        }
        if (target < 0 || i >= nums.length) {
            return;
        }

        cur.add(nums[i]);
        backtrack_1(nums, target - nums[i], cur, i);
        cur.remove(cur.size() - 1);
        backtrack_1(nums, target, cur, i + 1);
    }
    /*
        Time Complexity: O(2 ^ (t/m))
        Space Complexity: O(t/m)
     */

    /*
        * Approach: Backtracking Optimal
        *
     */
    public List<List<Integer>> combinationSum_2(int[] nums, int target) {
        res = new ArrayList<>();
        Arrays.sort(nums);

        dfs(0, new ArrayList<>(), 0, nums, target);
        return res;
    }

    private void dfs(int i, List<Integer> cur, int total, int[] nums, int target) {
        if (total == target) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int j = i; j < nums.length; j++) {
            if (total + nums[j] > target) {
                return;
            }
            cur.add(nums[j]);
            dfs(j, cur, total + nums[j], nums, target);
            cur.remove(cur.size() - 1);
        }
    }
    /*
        Time Complexity: O(2 ^ (t/m))
        Space Complexity: O(t/m)
     */
}
