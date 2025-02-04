package bitManipulation;

public class countingBits {
    /*
        * Approach: Bit Manipulation
        *
     */
    public int[] countBits_1(int n) {
        int[] res = new int[n + 1];
        for (int num = 1; num <= n; num++) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    res[num]++;
                }
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Bit Manipulation II
        *
     */
    public int[] countBits_2(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num != 0) {
                res[i]++;
                num &= (num - 1);
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
     * Approach: In-Built Functions
     *
     */
    public int[] countBits_3(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
     * Approach: Bit Manipulation DP
     *
     */
    public int[] countBits_4(int n){
        int[] dp = new int[n + 1];
        int offset = 1;

        for(int i = 1; i <= n; ++i){
            if(offset * 2 == i){
                offset = i;
            }

            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
     * Approach: Bit Manipulation DP Optimal
     *
     */
    public int[] countBits_5(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
