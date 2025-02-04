package stack;

import java.util.Stack;

public class largestRectangleInHistogram {
    /*
        * Approach: Brute Force
        *
     */
    public int largestRectangleArea_1(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int height = heights[i];

            int rightMost = i + 1;
            while (rightMost < n && heights[rightMost] >= height) {
                rightMost++;
            }

            int leftMost = i;
            while (leftMost >= 0 && heights[leftMost] >= height) {
                leftMost--;
            }

            rightMost--;
            leftMost++;
            maxArea = Math.max(maxArea, height * (rightMost - leftMost + 1));
        }
        return maxArea;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Divide and Conquer Segement Tree
        *
     */
    public class MinIdx_Segtree {
        int n;
        final int INF = (int) 1e9;
        int[] A, tree;

        public MinIdx_Segtree(int N, int[] heights) {
            this.n = N;
            this.A = heights;
            while (Integer.bitCount(n) != 1) {
                A = java.util.Arrays.copyOf(A, n + 1);
                A[n] = INF;
                n++;
            }
            tree = new int[2 * n];
            build();
        }

        public void build() {
            for (int i = 0; i < n; i++) {
                tree[n + i] = i;
            }
            for (int j = n - 1; j >= 1; j--) {
                int a = tree[j << 1];
                int b = tree[(j << 1) + 1];
                if (A[a] <= A[b]) {
                    tree[j] = a;
                } else {
                    tree[j] = b;
                }
            }
        }

        public void update(int i, int val) {
            A[i] = val;
            for (int j = (n + i) >> 1; j >= 1; j >>= 1) {
                int a = tree[j << 1];
                int b = tree[(j << 1) + 1];
                if (A[a] <= A[b]) {
                    tree[j] = a;
                } else {
                    tree[j] = b;
                }
            }
        }

        public int query(int ql, int qh) {
            return query(1, 0, n - 1, ql, qh);
        }

        public int query(int node, int l, int h, int ql, int qh) {
            if (ql > h || qh < l) return INF;
            if (l >= ql && h <= qh) return tree[node];
            int a = query(node << 1, l, (l + h) >> 1, ql, qh);
            int b = query((node << 1) + 1, ((l + h) >> 1) + 1, h, ql, qh);
            if (a == INF) return b;
            if (b == INF) return a;
            return A[a] <= A[b] ? a : b;
        }
    }

    public class Solution {
        public int getMaxArea(int[] heights, int l, int r, MinIdx_Segtree st) {
            if (l > r) return 0;
            if (l == r) return heights[l];

            int minIdx = st.query(l, r);
            return Math.max(Math.max(getMaxArea(heights, l, minIdx - 1, st),
                            getMaxArea(heights, minIdx + 1, r, st)),
                    (r - l + 1) * heights[minIdx]);
        }

        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            MinIdx_Segtree st = new MinIdx_Segtree(n, heights);
            return getMaxArea(heights, 0, n - 1, st);
        }
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Stack
        *
     */
    public int largestRectangleArea_3(int[] heights) {
        int n = heights.length;
        int[] leftMost = new int[n];
        int[] rightMost = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            leftMost[i] = -1;
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                leftMost[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            rightMost[i] = n;
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                rightMost[i] = stack.peek();
            }
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            leftMost[i] += 1;
            rightMost[i] -= 1;
            maxArea = Math.max(maxArea, heights[i] * (rightMost[i] - leftMost[i] + 1));
        }
        return maxArea;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Stack Optimal
        *
     */
    public int largestRectangleArea_4(int[] heights) {
        int maxArea = 0;
        Stack<int[]> stack = new Stack<>(); // pair: (index, height)

        for (int i = 0; i < heights.length; i++) {
            int start = i;
            while (!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                int[] top = stack.pop();
                int index = top[0];
                int height = top[1];
                maxArea = Math.max(maxArea, height * (i - index));
                start = index;
            }
            stack.push(new int[]{start, heights[i]});
        }

        for (int[] pair : stack) {
            int index = pair[0];
            int height = pair[1];
            maxArea = Math.max(maxArea, height * (heights.length - index));
        }
        return maxArea;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Stack One Pass
        *
     */
    public int largestRectangleArea_5(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() &&
                    (i == n || heights[stack.peek()] >= heights[i])) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
