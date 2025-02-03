package TwoPointers;

import java.util.HashMap;
import java.util.Map;

public class TwoSumIIInputArrayIsSorted {
    /*
        * Approach: Brute Force
        *
     */
    public int[] twoSum_1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[] { i + 1, j + 1 };
                }
            }
        }
        return new int[0];
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    /*
     * Approach: Binary Search
     *
     */
    public int[] twoSum_2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int l = i + 1, r = numbers.length - 1;
            int tmp = target - numbers[i];
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (numbers[mid] == tmp) {
                    return new int[] { i + 1, mid + 1 };
                } else if (numbers[mid] < tmp) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return new int[0];
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
    /*
     * Approach: Hash Map
     *
     */
    public int[] twoSum_3(int[] numbers, int target) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int tmp = target - numbers[i];
            if (mp.containsKey(tmp)) {
                return new int[] { mp.get(tmp), i + 1 };
            }
            mp.put(numbers[i], i + 1);
        }
        return new int[0];
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
     * Approach: Two Pointers
     *
     */
    public int[] twoSum_4(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;

        while (l < r) {
            int curSum = numbers[l] + numbers[r];

            if (curSum > target) {
                r--;
            } else if (curSum < target) {
                l++;
            } else {
                return new int[] { l + 1, r + 1 };
            }
        }
        return new int[0];
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
