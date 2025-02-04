package heapPriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInanArray {
    /*
        * Approach: Sorting
        *
     */
    public int findKthLargest_1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Min-Heap
        *
     */
    public int findKthLargest_2(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
    /*
        Time Complexity: O(n log k)
        Space Complexity: O(k)
     */
    /*
        * Approach: Quick Select
        *
     */
    public int findKthLargest_3(int[] nums, int k) {
        k = nums.length - k;

        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        int pivot = nums[right];
        int p = left;

        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }
        }

        int temp = nums[p];
        nums[p] = nums[right];
        nums[right] = temp;

        if (p > k) {
            return quickSelect(nums, left, p - 1, k);
        } else if (p < k) {
            return quickSelect(nums, p + 1, right, k);
        } else {
            return nums[p];
        }
    }
    /*
        Time complexity: O(n) in average case, O(n^2) in worst case.
        Space complexity: O(n)
     */
    /*
        * Approach: Quick Select Optimal
        *
     */
    private int partition(int[] nums, int left, int right) {
        int mid = (left + right) >> 1;
        swap(nums, mid, left + 1);

        if (nums[left] < nums[right])
            swap(nums, left, right);
        if (nums[left + 1] < nums[right])
            swap(nums, left + 1, right);
        if (nums[left] < nums[left + 1])
            swap(nums, left, left + 1);

        int pivot = nums[left + 1];
        int i = left + 1;
        int j = right;

        while (true) {
            while (nums[++i] > pivot);
            while (nums[--j] < pivot);
            if (i > j) break;
            swap(nums, i, j);
        }

        nums[left + 1] = nums[j];
        nums[j] = pivot;
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int quickSelect(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        while (true) {
            if (right <= left + 1) {
                if (right == left + 1 && nums[right] > nums[left])
                    swap(nums, left, right);
                return nums[k];
            }

            int j = partition(nums, left, right);

            if (j >= k) right = j - 1;
            if (j <= k) left = j + 1;
        }
    }

    public int findKthLargest_4(int[] nums, int k) {
        return quickSelect(nums, k - 1);
    }
    /*
        Time complexity: O(n) in average case, O(n^2) in worst case.
        Space complexity: O(1)
     */
}
