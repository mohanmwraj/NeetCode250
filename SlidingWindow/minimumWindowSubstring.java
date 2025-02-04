package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class minimumWindowSubstring {
    /*
        * Approach: Brute Force
        *
     */
    public String minWindow_1(String s, String t) {
        if (t.isEmpty()) return "";

        Map<Character, Integer> countT = new HashMap<>();
        for (char c : t.toCharArray()) {
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        int[] res = {-1, -1};
        int resLen = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> countS = new HashMap<>();
            for (int j = i; j < s.length(); j++) {
                countS.put(s.charAt(j), countS.getOrDefault(s.charAt(j), 0) + 1);

                boolean flag = true;
                for (char c : countT.keySet()) {
                    if (countS.getOrDefault(c, 0) < countT.get(c)) {
                        flag = false;
                        break;
                    }
                }

                if (flag && (j - i + 1) < resLen) {
                    resLen = j - i + 1;
                    res[0] = i;
                    res[1] = j;
                }
            }
        }

        return resLen == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1] + 1);
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(m)
     */

    /*
        * Approach: Sliding Window
        *
     */
    public String minWindow_2(String s, String t) {
        if (t.isEmpty()) return "";

        Map<Character, Integer> countT = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        int have = 0, need = countT.size();
        int[] res = {-1, -1};
        int resLen = Integer.MAX_VALUE;
        int l = 0;

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (countT.containsKey(c) && window.get(c).equals(countT.get(c))) {
                have++;
            }

            while (have == need) {
                if ((r - l + 1) < resLen) {
                    resLen = r - l + 1;
                    res[0] = l;
                    res[1] = r;
                }

                char leftChar = s.charAt(l);
                window.put(leftChar, window.get(leftChar) - 1);
                if (countT.containsKey(leftChar) && window.get(leftChar) < countT.get(leftChar)) {
                    have--;
                }
                l++;
            }
        }

        return resLen == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1] + 1);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(m)
     */
}
