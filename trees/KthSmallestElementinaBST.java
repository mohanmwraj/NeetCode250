package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class KthSmallestElementinaBST {
    /*
        * Approach: Brute Force
        *
     */
    public int kthSmallest_1(TreeNode root, int k) {
        List<Integer> arr = new ArrayList<>();

        dfs_1(root, arr);
        Collections.sort(arr);
        return arr.get(k - 1);
    }

    private void dfs_1(TreeNode node, List<Integer> arr) {
        if (node == null) {
            return;
        }

        arr.add(node.val);
        dfs_1(node.left, arr);
        dfs_1(node.right, arr);
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Inorder Traversal
        *
     */
    public int kthSmallest_2(TreeNode root, int k) {
        List<Integer> arr = new ArrayList<>();

        dfs_2(root, arr);
        return arr.get(k - 1);
    }

    private void dfs_2(TreeNode node, List<Integer> arr) {
        if (node == null) {
            return;
        }

        dfs_2(node.left, arr);
        arr.add(node.val);
        dfs_2(node.right, arr);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Recursive DFS (Optimal)
        *
     */
    public int kthSmallest_3(TreeNode root, int k) {
        int[] tmp = new int[2];
        tmp[0] = k;
        dfs_3(root, tmp);
        return tmp[1];
    }

    private void dfs_3(TreeNode node, int[] tmp) {
        if (node == null) {
            return;
        }

        dfs_3(node.left, tmp);
        tmp[0] -= 1;
        if (tmp[0] == 0) {
            tmp[1] = node.val;
            return;
        }
        dfs_3(node.right, tmp);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Iterative DFS Optimal
        *
     */
    public int kthSmallest_4(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }
            curr = curr.right;
        }

        return -1;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Morris Traversal
        *
     */
    public int kthSmallest_5(TreeNode root, int k) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                k--;
                if (k == 0) return curr.val;
                curr = curr.right;
            } else {
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr)
                    pred = pred.right;

                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    k--;
                    if (k == 0) return curr.val;
                    curr = curr.right;
                }
            }
        }
        return -1;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
