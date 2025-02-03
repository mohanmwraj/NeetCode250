package ArraysAndHashing;

import java.util.*;

public class groupAnagrams {
    /*
        * Approach: Sorting
        *
     */
    public List<List<String>> groupAnagrams_1(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);
            res.putIfAbsent(sortedS, new ArrayList<>());
            res.get(sortedS).add(s);
        }
        return new ArrayList<>(res.values());
    }
    /*
        Time Complexity: O(m * n log n)
        Space Complexity: O(m * N)
     */

    /*
        * Approach: Hash Table
        *
     */
    public List<List<String>> groupAnagrams_2(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            String key = Arrays.toString(count);
            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }
    /*
        Time Complexity: O(m * n)
        Space Complexity: O(m)
     */

}
