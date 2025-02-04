package trees;

import java.util.LinkedList;
import java.util.Queue;

public class validateBST {
    /*
        * Approach: Brute Force
        *
     */
    static boolean left_check(int val, int limit) {
        return val < limit;
    }

    static boolean right_check(int val, int limit) {
        return val > limit;
    }

    public boolean isValidBST_1(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValid(root.left, root.val, validateBST::left_check) ||
                !isValid(root.right, root.val, validateBST::right_check)) {
            return false;
        }

        return isValidBST_1(root.left) && isValidBST_1(root.right);
    }

    public boolean isValid(TreeNode root, int limit, CheckFunction check) {
        if (root == null) {
            return true;
        }
        if (!check.apply(root.val, limit)) {
            return false;
        }
        return isValid(root.left, limit, check) &&
                isValid(root.right, limit, check);
    }

    interface CheckFunction {
        boolean apply(int val, int limit);
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(n)
     */

    /*
        * Approach: Depth First Search
        *
     */
    public boolean isValidBST_2(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean valid(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }
        if (!(left < node.val && node.val < right)) {
            return false;
        }
        return valid(node.left, left, node.val) &&
                valid(node.right, node.val, right);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Breadth First Search
        *
     */
    public boolean isValidBST_3(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, Long.MIN_VALUE, Long.MAX_VALUE});

        while (!queue.isEmpty()) {
            Object[] current = queue.poll();
            TreeNode node = (TreeNode) current[0];
            long left = (long) current[1];
            long right = (long) current[2];

            if (!(left < node.val && node.val < right)) {
                return false;
            }

            if (node.left != null) {
                queue.offer(new Object[]{node.left, left, (long) node.val});
            }
            if (node.right != null) {
                queue.offer(new Object[]{node.right, (long) node.val, right});
            }
        }

        return true;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
