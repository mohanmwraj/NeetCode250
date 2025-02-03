package ArraysAndHashing;

import java.util.Arrays;
import java.util.HashMap;

public class validAnagram {
    /*
        * Approach: Sorting
        *
     */
    public boolean isAnagram_1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sSort = s.toCharArray();
        char[] tSort = t.toCharArray();
        Arrays.sort(sSort);
        Arrays.sort(tSort);
        return Arrays.equals(sSort, tSort);
    }
    /*
        Time Complexity: O(n log n + m log m)
        Space Complexity: O(1)
     */

    /*
        * Approach: Hash Table
        *
     */
    public boolean isAnagram_2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> countS = new HashMap<>();
        HashMap<Character, Integer> countT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            countS.put(s.charAt(i), countS.getOrDefault(s.charAt(i), 0) + 1);
            countT.put(t.charAt(i), countT.getOrDefault(t.charAt(i), 0) + 1);
        }
        return countS.equals(countT);
    }
    /*
        Time Complexity: O(n + m)
        Space Complexity: O(1)
     */

    /*
     * Approach: Hash Table Optimal
     *
     */
    public boolean isAnagram_3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int val : count) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }
    /*
        Time Complexity: O(n + m)
        Space Complexity: O(1)
     */
}
