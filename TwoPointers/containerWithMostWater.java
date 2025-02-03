package TwoPointers;

public class containerWithMostWater {
    /*
        * Approach: Brute Force
        *
     */
    public int maxArea_1(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                res = Math.max(res, Math.min(heights[i], heights[j]) * (j - i));
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Two Pointers
        *
     */
    public int maxArea_2(int[] heights) {
        int l = 0;
        int r = heights.length - 1;
        int res = 0;

        while (l < r) {
            int area = Math.min(heights[l], heights[r]) * (r - l);
            res = Math.max(res, area);
            if (heights[l] <= heights[r]) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
