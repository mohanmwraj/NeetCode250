package stack;

import java.util.Stack;

public class validParanthesis {
    /*
        * Approach: Brute Force
        *
     */
    public boolean isValid_1(String s) {
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        return s.isEmpty();
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(n)
     */

    /*
        * Approach: Stack
        *
     */
    public boolean isValid_2(String s) {
        Stack<Character> stack = new Stack<>();
        java.util.Map<Character, Character> closeToOpen = new java.util.HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');

        for (char c : s.toCharArray()) {
            if (closeToOpen.containsKey(c)) {
                if (!stack.isEmpty() && stack.peek() == closeToOpen.get(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
