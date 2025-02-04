package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class longestRepeatingCharacterReplacement {
    /*
        * Approach: Brute Force
        *
     */
    public int lengthOfLongestSubstring_1(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> charSet = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (charSet.contains(s.charAt(j))) {
                    break;
                }
                charSet.add(s.charAt(j));
            }
            res = Math.max(res, charSet.size());
        }
        return res;
    }
    /*
        Time Complexity: O(n * m)
        Space Complexity: O(m)
     */

    /*
        * Approach: Sliding Window
        *
     */
    public int lengthOfLongestSubstring_2(String s) {
        HashSet<Character> charSet = new HashSet<>();
        int l = 0;
        int res = 0;

        for (int r = 0; r < s.length(); r++) {
            while (charSet.contains(s.charAt(r))) {
                charSet.remove(s.charAt(l));
                l++;
            }
            charSet.add(s.charAt(r));
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(m)
     */
    /*
        * Approach: Sliding Window Optimal
        *
     */
    public int lengthOfLongestSubstring_3(String s) {
        HashMap<Character, Integer> mp = new HashMap<>();
        int l = 0, res = 0;

        for (int r = 0; r < s.length(); r++) {
            if (mp.containsKey(s.charAt(r))) {
                l = Math.max(mp.get(s.charAt(r)) + 1, l);
            }
            mp.put(s.charAt(r), r);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(m)
     */
}
