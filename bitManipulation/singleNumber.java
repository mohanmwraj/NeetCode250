package bitManipulation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class singleNumber {
    /*
        * Approach: Brute Force
        *
     */
    public int singleNumber_1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return nums[i];
            }
        }
        return -1;
    }
    /*
        Time Complexity: O(n ^ 2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Hash Set
        *
     */
    public int singleNumber_2(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                seen.remove(num);
            } else {
                seen.add(num);
            }
        }
        return seen.iterator().next();
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
     * Approach: sorting
     *
     */
    public int singleNumber_3(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] == nums[i + 1]) {
                i += 2;
            } else {
                return nums[i];
            }
        }
        return nums[i];
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Bit Manipulation
        *
     */
    public int singleNumber_4(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
