package bitManipulation;

public class numberOf1Bits {
    /*
        * Approach: Bit Mask - I
        *
     */
    public int hammingWeight_1(int n){
        int res = 0;
        for(int i = 0; i < 32; ++i){
            if((1 << i & n) != 0){
                res++;
            }
        }

        return res;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
    /*
        * Approach: Bit Mask II
        *
     */
    public int hammingWeight_2(int n){
        int res = 0;
        while(n != 0){
            res += (n & 1) == 1 ? 1 : 0;
            n >>= 1;
        }
        return res;
    }

    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
    /*
        * Approach: Bit Mask Optimal
        *
     */
    public int hammingWeight_3(int n){
        int res = 0;
        while(n != 0){
            n &= n - 1;
            res++;
        }

        return res;
    }
    /*
        Time Complexity: O(1)
        Space Complexity: O(1)
     */
    /*
        * Approach: Built-In function
        *
     */
    public int hammingWeight_4(int n){
        return Integer.bitCount(n);
    }
}
