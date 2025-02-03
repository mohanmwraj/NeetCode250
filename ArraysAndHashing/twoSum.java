package ArraysAndHashing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class twoSum {
    /*
     * Approach: Brute Force
     *
     */
    public int[] twoSum_1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
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
     * Approach: Sorting
     *
     */

    public int[] twoSum_2(int[] nums, int target) {
        int[][] A = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            A[i][0] = nums[i];
            A[i][1] = i;
        }

        Arrays.sort(A, Comparator.comparingInt(a -> a[0]));

        int i = 0, j = nums.length - 1;
        while (i < j) {
            int cur = A[i][0] + A[j][0];
            if (cur == target) {
                return new int[]{Math.min(A[i][1], A[j][1]),
                        Math.max(A[i][1], A[j][1])};
            } else if (cur < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[0];
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
    /*
     * Approach: Hash Map - Two Pass
     *
     */
    public int[] twoSum_3(int[] nums, int target) {
        Map<Integer, Integer> indices = new HashMap<>();  // val -> index

        for (int i = 0; i < nums.length; i++) {
            indices.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (indices.containsKey(diff) && indices.get(diff) != i) {
                return new int[]{i, indices.get(diff)};
            }
        }

        return new int[0];
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
     * Approach: Hash Map - One Pass
     *
     */
    public int[] twoSum_4(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; ++i){
            int diff = target - nums[i];
            if(map.containsKey(diff)){
                return new int[]{ map.get(diff), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
