package ArraysAndHashing;

import java.util.*;

/*
    Input: nums = [1,2,2,3,3,3], k = 2
    Output: [2,3]
 */
public class topKFrequentElements {
    /*
        * Approach: Sorting
        *
     */
    public int[] topKFrequent_1(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<int[]> arr = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            arr.add(new int[] {entry.getValue(), entry.getKey()});
        }
        arr.sort((a, b) -> b[0] - a[0]);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr.get(i)[1];
        }
        return res;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Heap
        *
     */
    public int[] topKFrequent_2(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            heap.offer(new int[]{entry.getValue(), entry.getKey()});
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[1];
        }
        return res;
    }

    /*
        Time Complexity: O(n log k)
        Space Complexity: O(k)
     */
    /*
        * Approach: Bucket Sort
        *
        * Maximum frequency of an element will be the length of the array, if every array element will
        * be the same element.
        * Frequency array will be the size of array length + 1, we store the element in the index as related to its frequencies,
        * From the frequency array, take the K element.
        *
     */
    public int[] topKFrequent(int[] nums, int k){
        Map<Integer, Integer> count = new HashMap<>();
        for(int num: nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] freq = new List[nums.length + 1];
        for(int i = 0; i < freq.length; ++i){
            freq[i] = new ArrayList<>();
        }
        for(Map.Entry<Integer, Integer> entry: count.entrySet()){
            freq[entry.getValue()].add(entry.getKey());
        }

        int[] result = new int[k];
        int index = 0;
        for(int i = freq.length - 1; i >= 0; ++i){
            for(int ele: freq[i]){
                result[index++] = ele;
                if(index == k) return result;
            }
        }
        return result;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
