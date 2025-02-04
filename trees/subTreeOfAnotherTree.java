package trees;

public class subTreeOfAnotherTree {
    /*
        * Approach: Depth First Search (DFS)
        *
     */
    public boolean isSubtree_1(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        if (sameTree(root, subRoot)) {
            return true;
        }
        return isSubtree_1(root.left, subRoot) ||
                isSubtree_1(root.right, subRoot);
    }

    public boolean sameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root != null && subRoot != null && root.val == subRoot.val) {
            return sameTree(root.left, subRoot.left) &&
                    sameTree(root.right, subRoot.right);
        }
        return false;
    }
    /*
        Time Complexity: O(m * n)
        Space Complexity: O(m + n)
     */

    /*
        * Approach: Searialization and Pattern Matching
        *
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "$#";
        }
        return "$" + root.val + serialize(root.left) + serialize(root.right);
    }

    public int[] z_function(String s) {
        int[] z = new int[s.length()];
        int l = 0, r = 0, n = s.length();
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    public boolean isSubtree_2(TreeNode root, TreeNode subRoot) {
        String serialized_root = serialize(root);
        String serialized_subRoot = serialize(subRoot);
        String combined = serialized_subRoot + "|" + serialized_root;

        int[] z_values = z_function(combined);
        int sub_len = serialized_subRoot.length();

        for (int i = sub_len + 1; i < combined.length(); i++) {
            if (z_values[i] == sub_len) {
                return true;
            }
        }
        return false;
    }
    /*
        Time Complexity: O(m + n)
        Space Complexity: O(m + n)
     */
}
