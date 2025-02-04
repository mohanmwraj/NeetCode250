package trees;

public class LCAOfBST {
    /*
        * Approach: Recursion
        *
     */
    public TreeNode lowestCommonAncestor_1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (Math.max(p.val, q.val) < root.val) {
            return lowestCommonAncestor_1(root.left, p, q);
        } else if (Math.min(p.val, q.val) > root.val) {
            return lowestCommonAncestor_1(root.right, p, q);
        } else {
            return root;
        }
    }
    /*
        Time Complexity: O(h)
        Space Complexity: O(h)
     */

    /*
        * Approach: Iteration
        *
     */
    public TreeNode lowestCommonAncestor_2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;

        while (cur != null) {
            if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right;
            } else if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left;
            } else {
                return cur;
            }
        }
        return null;
    }
    /*
        Time Complexity: O(h)
        Space Complexity: O(1)
     */
}
