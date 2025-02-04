package heapPriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class kthLargestElementInaStream {
    /*
        * Approach: Sorting
        *
     */
    class KthLargest_1 {
        List<Integer> arr;
        int K;
        public KthLargest_1(int k, int[] nums) {
            K = k;
            arr = new ArrayList();
            for (int i = 0; i < nums.length; i++) {
                arr.add(nums[i]);
            }
        }

        public int add(int val) {
            arr.add(val);
            Collections.sort(arr);
            return arr.get(arr.size() - K);
        }
    }
    /*
        Time Complexity: O(m * n log n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Min-Heap
        *
     */
    class KthLargest_2 {

        private PriorityQueue<Integer> minHeap;
        private int k;

        public KthLargest_2(int k, int[] nums) {
            this.k = k;
            this.minHeap = new PriorityQueue<>();
            for (int num : nums) {
                minHeap.offer(num);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        public int add(int val) {
            minHeap.offer(val);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }
    /*
        Time Complexity: O(m * log k)
        Space Complexity: O(k)
     */
}
