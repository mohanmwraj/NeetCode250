package mathAndGeometry;

import java.util.HashSet;
import java.util.Set;

public class happyNumber {
    /*
        * Approach: Hash Set
        *
     */
    public boolean isHappy_1(int n) {
        Set<Integer> visit = new HashSet<>();

        while (!visit.contains(n)) {
            visit.add(n);
            n = sumOfSquares(n);
            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    private int sumOfSquares(int n) {
        int output = 0;

        while (n > 0) {
            int digit = n % 10;
            digit = digit * digit;
            output += digit;
            n /= 10;
        }
        return output;
    }
    /*
        Time Complexity: O(log n)
        Space Complexity: O(log n)
     */
    /*
        * Approach: Fast and Slow Pointers I
        *
     */
    public boolean isHappy_2(int n) {
        int slow = n, fast = sumOfSquares(n);

        while (slow != fast) {
            fast = sumOfSquares(fast);
            fast = sumOfSquares(fast);
            slow = sumOfSquares(slow);
        }

        return fast == 1;
    }

    /*
        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
}
