package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class sameTree {
    /*
        * Approach: Depth First Search
        *
     */
    public boolean isSameTree_1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree_1(p.left, q.left) && isSameTree_1(p.right, q.right);
        } else {
            return false;
        }
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iterative DFS
        *
     */
    public boolean isSameTree_2(TreeNode p, TreeNode q) {
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{p, q});

        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            TreeNode node1 = nodes[0], node2 = nodes[1];

            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            stack.push(new TreeNode[]{node1.right, node2.right});
            stack.push(new TreeNode[]{node1.left, node2.left});
        }

        return true;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Beadth First Search
        *
     */
    public boolean isSameTree_3(TreeNode p, TreeNode q) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.add(p);
        q2.add(q);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            for (int i = q1.size(); i > 0; i--) {
                TreeNode nodeP = q1.poll();
                TreeNode nodeQ = q2.poll();

                if (nodeP == null && nodeQ == null) continue;
                if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val)
                    return false;

                q1.add(nodeP.left);
                q1.add(nodeP.right);
                q2.add(nodeQ.left);
                q2.add(nodeQ.right);
            }
        }

        return true;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
