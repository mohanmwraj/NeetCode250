package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;

public class longestSubstringWithoutRepeatingCharacters {
    /*
        * Approach: Brute Force
        *
     */
    public int characterReplacement_1(String s, int k) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> count = new HashMap<>();
            int maxf = 0;
            for (int j = i; j < s.length(); j++) {
                count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0) + 1);
                maxf = Math.max(maxf, count.get(s.charAt(j)));
                if ((j - i + 1) - maxf <= k) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n^2)
        Space complexity: O(m)
     */

    /*
        * Approach: Sliding Window
        *
     */
    public int characterReplacement_2(String s, int k) {
        int res = 0;
        HashSet<Character> charSet = new HashSet<>();
        for (char c : s.toCharArray()) {
            charSet.add(c);
        }

        for (char c : charSet) {
            int count = 0, l = 0;
            for (int r = 0; r < s.length(); r++) {
                if (s.charAt(r) == c) {
                    count++;
                }

                while ((r - l + 1) - count > k) {
                    if (s.charAt(l) == c) {
                        count--;
                    }
                    l++;
                }

                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }
    /*
        Time Complexity: O(m * n)
        Space complexity: O(m)
     */
    /*
        * Approach: Sliding Window Optimal
        *
     */
    public int characterReplacement_3(String s, int k) {
        HashMap<Character, Integer> count = new HashMap<>();
        int res = 0;

        int l = 0, maxf = 0;
        for (int r = 0; r < s.length(); r++) {
            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
            maxf = Math.max(maxf, count.get(s.charAt(r)));

            while ((r - l + 1) - maxf > k) {
                count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
                l++;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
    /*
        Time Complexity: O(n)
        Space complexity: O(m)
     */
}
