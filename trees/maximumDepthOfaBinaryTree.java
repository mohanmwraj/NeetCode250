package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
class Pair<First, Second> {
    private First first;
    private Second second;

    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public void setSecond(Second second) {
        this.second = second;
    }

    public First getKey() {
        return first;
    }

    public Second getValue() {
        return second;
    }

    public void set(First first, Second second) {
        setFirst(first);
        setSecond(second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
        if (second != null ? !second.equals(pair.second) : pair.second != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}

public class maximumDepthOfaBinaryTree {
    /*
        * Approach: Recursive DFS
        *
     */
    public int maxDepth_1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth_1(root.left), maxDepth_1(root.right));
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(h)
     */
    /*
        * Approach: Iterative DFS Stack
        *
     */
    public int maxDepth_2(TreeNode root) {
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));
        int res = 0;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pop();
            TreeNode node = current.getKey();
            int depth = current.getValue();

            if (node != null) {
                res = Math.max(res, depth);
                stack.push(new Pair<>(node.left, depth + 1));
                stack.push(new Pair<>(node.right, depth + 1));
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(h)
     */
    /*
        * Approach: Breadth First Search
        *
     */
    public int maxDepth_3(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
        }

        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            level++;
        }
        return level;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(h)
     */
}
