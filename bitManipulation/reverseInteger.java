package bitManipulation;

public class reverseInteger {
    /*
        * Approach: Brute Force
        *
     */
    public int reverse_1(int x) {
        int org = x;
        x = Math.abs(x);
        long res = Long.parseLong(new StringBuilder(
                String.valueOf(x)).reverse().toString()
        );
        if (org < 0) {
            res *= -1;
        }
        if (res < -(1 << 31) || res > (1 << 31) - 1) {
            return 0;
        }
        return (int)res;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */

    /*
        * Approach: Recursion
        *
     */
    public int reverse_2(int x) {
        long res = rec(Math.abs(x), 0) * (x < 0 ? -1 : 1);
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) res;
    }

    private long rec(int n, long rev) {
        if (n == 0) {
            return rev;
        }
        rev = rev * 10 + n % 10;
        return rec(n / 10, rev);
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
    /*
        * Approach: Iteration
        *
     */
    public int reverse_3(int x) {
        final int MIN = -2147483648; // -2^31
        final int MAX = 2147483647;  // 2^31 - 1

        int res = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            if (res > MAX / 10 || (res == MAX / 10 && digit > MAX % 10))
                return 0;
            if (res < MIN / 10 || (res == MIN / 10 && digit < MIN % 10))
                return 0;
            res = (res * 10) + digit;
        }

        return res;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
}
