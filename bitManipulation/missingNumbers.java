package bitManipulation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class missingNumbers {
    /*
        * Approach: Sorting
        *
     */
    public int missingNumber_1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Hash Set
        *
     */
    public int missingNumber_2(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Bitwise XOR
        *
     */
    public int missingNumber_3(int[] nums) {
        int n = nums.length;
        int xorr = n;
        for (int i = 0; i < n; i++) {
            xorr ^= i ^ nums[i];
        }
        return xorr;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Math
        *
     */
    public int missingNumber_4(int[] nums) {
        int res = nums.length;

        for (int i = 0; i < nums.length; i++) {
            res += i - nums[i];
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
