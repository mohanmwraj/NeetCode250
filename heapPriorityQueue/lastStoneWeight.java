package heapPriorityQueue;

import java.util.*;

public class lastStoneWeight {
    /*
        * Approach: Sorting
        *
     */
    public int lastStoneWeight_1(int[] stones) {
        List<Integer> stoneList = new ArrayList<>();
        for (int stone : stones) {
            stoneList.add(stone);
        }

        while (stoneList.size() > 1) {
            Collections.sort(stoneList);
            int cur = stoneList.remove(stoneList.size() - 1) -
                    stoneList.remove(stoneList.size() - 1);
            if (cur != 0) {
                stoneList.add(cur);
            }
        }

        return stoneList.isEmpty() ? 0 : stoneList.get(0);
    }
    /*
        Time Complexity: O(n^2 log n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Binary Search
        *
     */
    public int lastStoneWeight_2(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;

        while (n > 1) {
            int cur = stones[n - 1] - stones[n - 2];
            n -= 2;
            if (cur > 0) {
                int l = 0, r = n;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (stones[mid] < cur) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                int pos = l;
                n++;
                stones = Arrays.copyOf(stones, n);
                for (int i = n - 1; i > pos; i--) {
                    stones[i] = stones[i - 1];
                }
                stones[pos] = cur;
            }
        }
        return n > 0 ? stones[0] : 0;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Heap
        *
     */
    public int lastStoneWeight_3(int[] stones) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int s : stones) {
            minHeap.offer(-s);
        }

        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            if (second > first) {
                minHeap.offer(first - second);
            }
        }

        minHeap.offer(0);
        return Math.abs(minHeap.peek());
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Bucket Sort
        *
     */
    public int lastStoneWeight_4(int[] stones) {
        int maxStone = 0;
        for (int stone : stones) {
            maxStone = Math.max(maxStone, stone);
        }

        int[] bucket = new int[maxStone + 1];
        for (int stone : stones) {
            bucket[stone]++;
        }

        int first = maxStone, second = maxStone;
        while (first > 0) {
            if (bucket[first] % 2 == 0) {
                first--;
                continue;
            }

            int j = Math.min(first - 1, second);
            while (j > 0 && bucket[j] == 0) {
                j--;
            }

            if (j == 0) {
                return first;
            }

            second = j;
            bucket[first]--;
            bucket[second]--;
            bucket[first - second]++;
            first = Math.max(first - second, second);
        }

        return first;
    }
    /*
        Time Complexity: O(n + w)
        Space Complexity: O(w)
     */
}
