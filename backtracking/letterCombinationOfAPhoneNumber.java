package backtracking;

import java.util.ArrayList;
import java.util.List;

public class letterCombinationOfAPhoneNumber {
    /*
        * Approach: Backtracking
        *
     */
    private List<String> res = new ArrayList<>();
    private String[] digitToChar = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "qprs", "tuv", "wxyz"
    };

    public List<String> letterCombinations_1(String digits) {
        if (digits.isEmpty()) return res;
        backtrack(0, "", digits);
        return res;
    }

    private void backtrack(int i, String curStr, String digits) {
        if (curStr.length() == digits.length()) {
            res.add(curStr);
            return;
        }
        String chars = digitToChar[digits.charAt(i) - '0'];
        for (char c : chars.toCharArray()) {
            backtrack(i + 1, curStr + c, digits);
        }
    }
    /*
        Time Complexity: O(n * 4^n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Iteration
        *
     */
    public List<String> letterCombinations_2(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();

        List<String> res = new ArrayList<>();
        res.add("");
        String[] digitToChar = {
                "", "", "abc", "def", "ghi", "jkl",
                "mno", "qprs", "tuv", "wxyz"
        };

        for (char digit : digits.toCharArray()) {
            List<String> tmp = new ArrayList<>();
            for (String curStr : res) {
                for (char c : digitToChar[digit - '0'].toCharArray()) {
                    tmp.add(curStr + c);
                }
            }
            res = tmp;
        }
        return res;
    }
    /*
        Time Complexity: O(n * 4^n)
        Space Complexity: O(n)
     */
}
