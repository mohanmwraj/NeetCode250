package SlidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class slidingWindowMaximum {
    /*
        * Approach: Brute Force
        *
     */
    public int[] maxSlidingWindow_1(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int maxi = nums[i];
            for (int j = i; j < i + k; j++) {
                maxi = Math.max(maxi, nums[j]);
            }
            output[i] = maxi;
        }

        return output;
    }
    /*
        Time Complexity: O( n * k)
        Space complexity: O(1)
     */

    /*
        * Approach: Segment Tree
        *
     */
    class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int N, int[] A) {
            this.n = N;
            while (Integer.bitCount(n) != 1) {
                n++;
            }
            build(N, A);
        }

        void build(int N, int[] A) {
            tree = new int[2 * n];
            Arrays.fill(tree, Integer.MIN_VALUE);
            for (int i = 0; i < N; i++) {
                tree[n + i] = A[i];
            }
            for (int i = n - 1; i > 0; --i) {
                tree[i] = Math.max(tree[i << 1], tree[i << 1 | 1]);
            }
        }

        int query(int l, int r) {
            int res = Integer.MIN_VALUE;
            for (l += n, r += n + 1; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1) res = Math.max(res, tree[l++]);
                if ((r & 1) == 1) res = Math.max(res, tree[--r]);
            }
            return res;
        }
    }

    public class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            SegmentTree segTree = new SegmentTree(n, nums);
            int[] output = new int[n - k + 1];
            for (int i = 0; i <= n - k; i++) {
                output[i] = segTree.query(i, i + k - 1);
            }
            return output;
        }
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Heap
        *
     */
    public int[] maxSlidingWindow_3(int[] nums, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int[] output = new int[nums.length - k + 1];
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            heap.offer(new int[]{nums[i], i});
            if (i >= k - 1) {
                while (heap.peek()[1] <= i - k) {
                    heap.poll();
                }
                output[idx++] = heap.peek()[0];
            }
        }
        return output;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Dynamic Programming
        *
     */
    public int[] maxSlidingWindow_4(int[] nums, int k) {
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = nums[0];
        rightMax[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
            if (i % k == 0) {
                leftMax[i] = nums[i];
            } else {
                leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
            }

            if ((n - 1 - i) % k == 0) {
                rightMax[n - 1 - i] = nums[n - 1 - i];
            } else {
                rightMax[n - 1 - i] = Math.max(rightMax[n - i], nums[n - 1 - i]);
            }
        }

        int[] output = new int[n - k + 1];

        for (int i = 0; i < n - k + 1; i++) {
            output[i] = Math.max(leftMax[i + k - 1], rightMax[i]);
        }

        return output;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Deque
        *
     */
    public int[] maxSlidingWindow_5(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];
        Deque<Integer> q = new LinkedList<>();
        int l = 0, r = 0;

        while (r < n) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            q.addLast(r);

            if (l > q.getFirst()) {
                q.removeFirst();
            }

            if ((r + 1) >= k) {
                output[l] = nums[q.getFirst()];
                l++;
            }
            r++;
        }

        return output;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
