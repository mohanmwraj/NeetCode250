package binarySearch;

import java.util.Arrays;

public class findMinimumInRotatedSortedArray {
    /*
        * Approach: Brute Force
        *
     */
    public int findMin_1(int[] nums) {
        return Arrays.stream(nums).min().getAsInt();
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Binary Search
        *
     */
    public int findMin_2(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int res = nums[0];

        while (l <= r) {
            if (nums[l] < nums[r]) {
                res = Math.min(res, nums[l]);
                break;
            }

            int m = l + (r - l) / 2;
            res = Math.min(res, nums[m]);
            if (nums[m] >= nums[l]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Binary Search Lower Bound
        *
     */
    public int findMin_3(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < nums[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return nums[l];
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
}
