package backtracking;

import java.util.*;

public class subsetII {
    /*
        * Approach: Brute Force
        *
     */
    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> subsetsWithDup_1(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>());
        return new ArrayList<>(res);
    }

    private void backtrack(int[] nums, int i, List<Integer> subset) {
        if (i == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[i]);
        backtrack(nums, i + 1, subset);
        subset.remove(subset.size() - 1);
        backtrack(nums, i + 1, subset);
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(2^n)
     */

    /*
        * Approach: Backtracking - Pick / Not Pick
        *
     */
    List<List<Integer>> res1 = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup_2(int[] nums) {
        Arrays.sort(nums);
        backtrack(0, new ArrayList<>(), nums);
        return res1;
    }

    private void backtrack(int i, List<Integer> subset, int[] nums) {
        if (i == nums.length) {
            res1.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[i]);
        backtrack(i + 1, subset, nums);
        subset.remove(subset.size() - 1);

        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }
        backtrack(i + 1, subset, nums);
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(2^n)
     */

    /*
        * Approach: Backtracking
        *
     */
    List<List<Integer>> res2 = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup_3(int[] nums) {
        Arrays.sort(nums);
        backtrack_3(0, new ArrayList<>(), nums);
        return res2;
    }

    private void backtrack_3(int i, List<Integer> subset, int[] nums) {
        res.add(new ArrayList<>(subset));
        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            subset.add(nums[j]);
            backtrack(j + 1, subset, nums);
            subset.remove(subset.size() - 1);
        }
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(2^n)
     */

    /*
        * Approach: Iteration
        *
     */
    public List<List<Integer>> subsetsWithDup_4(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int prevIdx = 0;
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            idx = (i >= 1 && nums[i] == nums[i - 1]) ? prevIdx : 0;
            prevIdx = res.size();
            for (int j = idx; j < prevIdx; j++) {
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n * 2^n)
        Space Complexity: O(1)
     */
}
