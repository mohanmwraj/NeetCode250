package binarySearch;

import java.util.Arrays;

public class kokEatingBananas {
    /*
        * Approach: Brute Force
        *
     */
    public int minEatingSpeed_1(int[] piles, int h) {
        int speed = 1;
        while (true) {
            long totalTime = 0;
            for (int pile : piles) {
                totalTime += (int) Math.ceil((double) pile / speed);
            }

            if (totalTime <= h) {
                return speed;
            }
            speed++;
        }
    }
    /*
        Time Complexity: O(m * n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Binary Search
        *
     */
    public int minEatingSpeed_2(int[] piles, int h) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt();
        int res = r;

        while (l <= r) {
            int k = (l + r) / 2;

            long totalTime = 0;
            for (int p : piles) {
                totalTime += Math.ceil((double) p / k);
            }
            if (totalTime <= h) {
                res = k;
                r = k - 1;
            } else {
                l = k + 1;
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n * log m)
        Space Complexity: O(1)
     */
}
