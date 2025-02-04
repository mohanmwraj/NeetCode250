package heapPriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class findMedianFromDataStream {
    /*
        * Approach: Sorting
        *
     */
    public class MedianFinder_1 {
        private ArrayList<Integer> data;

        public MedianFinder_1() {
            data = new ArrayList<>();
        }

        public void addNum(int num) {
            data.add(num);
        }

        public double findMedian() {
            Collections.sort(data);
            int n = data.size();
            if ((n & 1) == 1) {
                return data.get(n / 2);
            } else {
                return (data.get(n / 2) + data.get(n / 2 - 1)) / 2.0;
            }
        }
    }
    /*
        Time complexity: O(m) for addNum(), O(m∗nlog n) for findMedian().
        Space complexity: O(n)
     */

    /*
        * Approach: Heap
        *
     */
    public class MedianFinder_2 {

        private Queue<Integer> smallHeap; //small elements - maxHeap
        private Queue<Integer> largeHeap; //large elements - minHeap

        public MedianFinder_2() {
            smallHeap = new PriorityQueue<>((a, b) -> b - a);
            largeHeap = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            smallHeap.add(num);
            if (
                    smallHeap.size() - largeHeap.size() > 1 ||
                            !largeHeap.isEmpty() &&
                                    smallHeap.peek() > largeHeap.peek()
            ) {
                largeHeap.add(smallHeap.poll());
            }
            if (largeHeap.size() - smallHeap.size() > 1) {
                smallHeap.add(largeHeap.poll());
            }
        }

        public double findMedian() {
            if (smallHeap.size() == largeHeap.size()) {
                return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
            } else if (smallHeap.size() > largeHeap.size()) {
                return (double) smallHeap.peek();
            } else {
                return (double) largeHeap.peek();
            }
        }
    }
    /*
        Time complexity: O(m * log n) for addNum(), O(m) for findMedian().
        Space complexity: O(n)
     */
}
