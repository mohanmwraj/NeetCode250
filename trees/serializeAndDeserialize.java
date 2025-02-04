package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class serializeAndDeserialize {
    /*
        * Approach: Depth First Search
        *
     */
    public class Codec_1 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<String> res = new ArrayList<>();
            dfsSerialize(root, res);
            return String.join(",", res);
        }

        private void dfsSerialize(TreeNode node, List<String> res) {
            if (node == null) {
                res.add("N");
                return;
            }
            res.add(String.valueOf(node.val));
            dfsSerialize(node.left, res);
            dfsSerialize(node.right, res);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] vals = data.split(",");
            int[] i = {0};
            return dfsDeserialize(vals, i);
        }

        private TreeNode dfsDeserialize(String[] vals, int[] i) {
            if (vals[i[0]].equals("N")) {
                i[0]++;
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(vals[i[0]]));
            i[0]++;
            node.left = dfsDeserialize(vals, i);
            node.right = dfsDeserialize(vals, i);
            return node;
        }
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Breadth First Search
        *
     */
    public class Codec_2 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "N";
            StringBuilder res = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    res.append("N,");
                } else {
                    res.append(node.val).append(",");
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] vals = data.split(",");
            if (vals[0].equals("N")) return null;
            TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int index = 1;

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (!vals[index].equals("N")) {
                    node.left = new TreeNode(Integer.parseInt(vals[index]));
                    queue.add(node.left);
                }
                index++;
                if (!vals[index].equals("N")) {
                    node.right = new TreeNode(Integer.parseInt(vals[index]));
                    queue.add(node.right);
                }
                index++;
            }
            return root;
        }
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
