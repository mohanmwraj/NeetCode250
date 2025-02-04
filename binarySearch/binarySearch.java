package binarySearch;

import java.util.Arrays;

public class binarySearch {
    /*
        * Approach: Recursive Binary Search
        *
     */
    public int binary_search_1(int l, int r, int[] nums, int target) {
        if (l > r) return -1;
        int m = l + (r - l) / 2;

        if (nums[m] == target) return m;
        return (nums[m] < target) ?
                binary_search_1(m + 1, r, nums, target) :
                binary_search_1(l, m - 1, nums, target);
    }

    public int search_1(int[] nums, int target) {
        return binary_search_1(0, nums.length - 1, nums, target);
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(log n)
     */
    /*
        * Approach: Iterative Binary Search
        *
     */
    public int search_2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int m = l + ((r - l) / 2);
            if (nums[m] > target) {
                r = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Upper Bound
        *
     */
    public int search_3(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r) {
            int m = l + ((r - l) / 2);
            if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return (l > 0 && nums[l - 1] == target) ? l - 1 : -1;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Lower Bound
        *
     */
    public int search_4(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return (l < nums.length && nums[l] == target) ? l : -1;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Built In Function
        *
     */
    public int search(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        return index >= 0 ? index : -1;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */

}
