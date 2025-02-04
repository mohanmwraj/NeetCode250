package Intervals;

import java.util.*;

public class meetingRoomsII {
    /*
        * Approach: Min Heap
        *
     */
    public int minMeetingRooms_1(List<Interval> intervals) {
        intervals.sort((a, b) -> a.start - b.start);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Interval interval : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval.start) {
                minHeap.poll();
            }
            minHeap.offer(interval.end);
        }
        return minHeap.size();
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Sweep Line Algorithm
        *
     */
    public int minMeetingRooms_2(List<Interval> intervals) {
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for (Interval i : intervals) {
            mp.put(i.start, mp.getOrDefault(i.start, 0) + 1);
            mp.put(i.end, mp.getOrDefault(i.end, 0) - 1);
        }
        int prev = 0;
        int res = 0;
        for (int key : mp.keySet()) {
            prev += mp.get(key);
            res = Math.max(res, prev);
        }
        return res;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Two Pointers
        *
     */
    public int minMeetingRooms_3(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int res = 0, count = 0, s = 0, e = 0;
        while (s < n) {
            if (start[s] < end[e]) {
                s++;
                count++;
            } else {
                e++;
                count--;
            }
            res = Math.max(res, count);
        }
        return res;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Greedy
        *
     */
    public int minMeetingRooms_4(List<Interval> intervals) {
        List<int[]> time = new ArrayList<>();
        for (Interval i : intervals) {
            time.add(new int[] { i.start, 1 });
            time.add(new int[] { i.end, -1 });
        }

        time.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int res = 0, count = 0;
        for (int[] t : time) {
            count += t[1];
            res = Math.max(res, count);
        }
        return res;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */
}
