package trees;

import java.util.LinkedList;
import java.util.Queue;

public class countGoodNodesinBT {
    /*
        * Approach: Depth First Search
        *
     */
    public int goodNodes_1(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxVal) {
        if (node == null) {
            return 0;
        }

        int res = (node.val >= maxVal) ? 1 : 0;
        maxVal = Math.max(maxVal, node.val);
        res += dfs(node.left, maxVal);
        res += dfs(node.right, maxVal);
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Breadth First Search
        *
     */
    public int goodNodes_2(TreeNode root) {
        int res = 0;
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(root, Integer.MIN_VALUE));

        while (!q.isEmpty()) {
            Pair<TreeNode, Integer> pair = q.poll();
            TreeNode node = pair.getKey();
            int maxval = pair.getValue();
            if (node.val >= maxval) {
                res++;
            }
            if (node.left != null) {
                q.offer(new Pair<>(node.left, Math.max(maxval, node.val)));
            }
            if (node.right != null) {
                q.offer(new Pair<>(node.right, Math.max(maxval, node.val)));
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
