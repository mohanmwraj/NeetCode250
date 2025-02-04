package trees;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class balancedBinaryTree {
    /*
        * Approach: Brute Force
        *
     */
    public boolean isBalanced_1(TreeNode root) {
        if (root == null) return true;

        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1) return false;
        return isBalanced_1(root.left) && isBalanced_1(root.right);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(n)
     */

    /*
        * Approach: Depth First Search
        *
     */
    public boolean isBalanced_2(TreeNode root) {
        return dfs(root)[0] == 1;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        boolean balanced = (left[0] == 1 && right[0] == 1) &&
                (Math.abs(left[1] - right[1]) <= 1);
        int height = 1 + Math.max(left[1], right[1]);

        return new int[]{balanced ? 1 : 0, height};
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(h)
     */
    /*
        * Approach: Depth First Search (Stack)
        *
     */
    public boolean isBalanced(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root, last = null;
        Map<TreeNode, Integer> depths = new HashMap<>();

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.peek();
                if (node.right == null || last == node.right) {
                    stack.pop();
                    int left = depths.getOrDefault(node.left, 0);
                    int right = depths.getOrDefault(node.right, 0);
                    if (Math.abs(left - right) > 1) return false;
                    depths.put(node, 1 + Math.max(left, right));
                    last = node;
                    node = null;
                } else {
                    node = node.right;
                }
            }
        }
        return true;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
