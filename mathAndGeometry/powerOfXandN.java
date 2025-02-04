package mathAndGeometry;

public class powerOfXandN {
    /*
        * Approach: Brute Force
        *
     */
    public double myPow_1(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        double res = 1;
        for (int i = 0; i < Math.abs(n); i++) {
            res *= x;
        }
        return n >= 0 ? res : 1 / res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Binary Exponentiation Recursive
        *
     */
    public double myPow_2(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        double res = helper(x, Math.abs((long) n));
        return (n >= 0) ? res : 1 / res;
    }

    private double helper(double x, long n) {
        if (n == 0) {
            return 1;
        }
        double half = helper(x, n / 2);
        return (n % 2 == 0) ? half * half : x * half * half;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(log n)
     */
    /*
         * Approach: Binary Exponentiation Iterative
         *
     */
    public double myPow_3(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;

        double res = 1;
        long power = Math.abs((long)n);

        while (power > 0) {
            if ((power & 1) == 1) {
                res *= x;
            }
            x *= x;
            power >>= 1;
        }

        return n >= 0 ? res : 1 / res;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
}
