package linkedList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class findTheDuplicateNumber {
    /*
        * Approach: Sorting
        *
     */
    public int findDuplicate_1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Hash Set
        *
     */
    public int findDuplicate_2(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }
        return -1;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Array
     */
    public int findDuplicate_3(int[] nums) {
        int[] seen = new int[nums.length];
        for (int num : nums) {
            if (seen[num - 1] == 1) {
                return num;
            }
            seen[num - 1] = 1;
        }
        return -1;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Negative Marking
        *
     */
    public int findDuplicate_4(int[] nums) {
        for (int num : nums) {
            int idx = Math.abs(num) - 1;
            if (nums[idx] < 0) {
                return Math.abs(num);
            }
            nums[idx] *= -1;
        }
        return -1;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Binary Search
        *
     */
    public int findDuplicate_5(int[] nums) {
        int n = nums.length;
        int low = 1;
        int high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int lessOrEqual = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    lessOrEqual++;
                }
            }

            if (lessOrEqual <= mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Bit Manipulation
        *
     */
    public int findDuplicate_6(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int b = 0; b < 32; b++) {
            int x = 0, y = 0;
            int mask = 1 << b;
            for (int num : nums) {
                if ((num & mask) != 0) {
                    x++;
                }
            }
            for (int num = 1; num < n; num++) {
                if ((num & mask) != 0) {
                    y++;
                }
            }
            if (x > y) {
                res |= mask;
            }
        }
        return res;
    }
    /*
        Time Complexity: O(32 * n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Fast and Slow Pointers
        *
     */
    public int findDuplicate_7(int[] nums) {
        int slow = 0, fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        int slow2 = 0;
        while (true) {
            slow = nums[slow];
            slow2 = nums[slow2];
            if (slow == slow2) {
                return slow;
            }
        }
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
