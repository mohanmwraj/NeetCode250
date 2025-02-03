package ArraysAndHashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class containsDuplicate {
    /*
        * Approach: Brute Force
        *
     */
    public boolean hasDuplicate_1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    /*
        Time complexity: O(n^2)
        Space complexity: O(1)
     */
    /*
        * Approach: Sorting
        *
     */
    public boolean hasDuplicate_2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
    /*
        Time complexity: O(n log n)
        Space complexity: O(1)
     */
    /*
     * Approach: Hash Set
     *
     */
    public boolean hasDuplicate_3(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */
    /*
     * Approach: Hash Set Length
     *
     */
    public boolean hasDuplicate_4(int[] nums) {
        return Arrays.stream(nums).distinct().count() < nums.length;
    }
    /*
        Time complexity: O(n)
        Space complexity: O(n)
     */
}
