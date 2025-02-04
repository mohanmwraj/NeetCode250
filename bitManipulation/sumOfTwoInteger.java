package bitManipulation;

public class sumOfTwoInteger {
    /*
        * Approach: Brute Force
        *
     */
    public int getSum_1(int a, int b) {
        return a + b;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */

    /*
        * Approach: Bit Manipulation
        *
     */
    public int getSum_2(int a, int b) {
        int carry = 0, res = 0, mask = 0xFFFFFFFF;

        for (int i = 0; i < 32; i++) {
            int a_bit = (a >> i) & 1;
            int b_bit = (b >> i) & 1;
            int cur_bit = a_bit ^ b_bit ^ carry;
            carry = (a_bit + b_bit + carry) >= 2 ? 1 : 0;
            if (cur_bit != 0) {
                res |= (1 << i);
            }
        }

        if (res > 0x7FFFFFFF) {
            res = ~(res ^ mask);
        }

        return res;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
    /*
        * Approach: Bit Manipulation Optimal
        *
     */
    public int getSum_3(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a ^= b;
            b = carry;
        }
        return a;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */

}
