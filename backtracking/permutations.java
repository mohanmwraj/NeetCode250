package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class permutations {
    /*
        * Approach: Recursion
        *
     */
    public List<List<Integer>> permute_1(int[] nums) {
        if (nums.length == 0) {
            return Arrays.asList(new ArrayList<>());
        }

        List<List<Integer>> perms = permute_1(Arrays.copyOfRange(nums, 1, nums.length));
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> p : perms) {
            for (int i = 0; i <= p.size(); i++) {
                List<Integer> p_copy = new ArrayList<>(p);
                p_copy.add(i, nums[0]);
                res.add(p_copy);
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n! * n^2)
        Space Complexity: O(n! * n)
     */
    /*
        * Approach: Iteration
        *
     */
    public List<List<Integer>> permute_2(int[] nums) {
        List<List<Integer>> perms = new ArrayList<>();
        perms.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> new_perms = new ArrayList<>();
            for (List<Integer> p : perms) {
                for (int i = 0; i <= p.size(); i++) {
                    List<Integer> p_copy = new ArrayList<>(p);
                    p_copy.add(i, num);
                    new_perms.add(p_copy);
                }
            }
            perms = new_perms;
        }
        return perms;
    }
    /*
        Time Complexity: O(n! * n^2)
        Space Complexity: O(n! * n)
     */
    /*
        * Approach: Backtracking
        *
     */
    public class Solution_3 {
        List<List<Integer>> res;
        public List<List<Integer>> permute_3(int[] nums) {
            res = new ArrayList<>();
            backtrack(new ArrayList<>(), nums, new boolean[nums.length]);
            return res;
        }

        public void backtrack(List<Integer> perm, int[] nums, boolean[] pick) {
            if (perm.size() == nums.length) {
                res.add(new ArrayList<>(perm));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (!pick[i]) {
                    perm.add(nums[i]);
                    pick[i] = true;
                    backtrack(perm, nums, pick);
                    perm.remove(perm.size() - 1);
                    pick[i] = false;
                }
            }
        }
    }
    /*
        Time Complexity: O(n! * n)
        Space Complexity: O(n! * n)
     */
    /*
        * Approach: Backtracking Bit Mask
        *
     */
    public class Solution_4 {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            backtrack(new ArrayList<>(), nums, 0);
            return res;
        }

        private void backtrack(List<Integer> perm, int[] nums, int mask) {
            if (perm.size() == nums.length) {
                res.add(new ArrayList<>(perm));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if ((mask & (1 << i)) == 0) {
                    perm.add(nums[i]);
                    backtrack(perm, nums, mask | (1 << i));
                    perm.remove(perm.size() - 1);
                }
            }
        }
    }
    /*
        Time Complexity: O(n! * n)
        Space Complexity: O(n! * n)
     */
    /*
        * Approach: Backtracking Optimal
        *
     */
    public class Solution_5 {
        List<List<Integer>> res;
        public List<List<Integer>> permute_5(int[] nums) {
            res = new ArrayList<>();
            backtrack(nums, 0);
            return res;
        }

        public void backtrack(int[] nums, int idx) {
            if (idx == nums.length) {
                List<Integer> perm = new ArrayList<>();
                for (int num : nums) perm.add(num);
                res.add(perm);
                return;
            }
            for (int i = idx; i < nums.length; i++) {
                swap(nums, idx, i);
                backtrack(nums, idx + 1);
                swap(nums, idx, i);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    /*
        Time Complexity: O(n! * n)
        Space Complexity: O(n! * n)
     */
}
