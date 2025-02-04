package heapPriorityQueue;

import java.util.*;

public class taskScheduler {
    /*
        * Approach: Brute Force
        *
     */
    public int leastInterval_1(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                arr.add(new int[]{count[i], i});
            }
        }

        int time = 0;
        List<Integer> processed = new ArrayList<>();
        while (!arr.isEmpty()) {
            int maxi = -1;
            for (int i = 0; i < arr.size(); i++) {
                boolean ok = true;
                for (int j = Math.max(0, time - n); j < time; j++) {
                    if (j < processed.size() && processed.get(j) == arr.get(i)[1]) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) continue;
                if (maxi == -1 || arr.get(maxi)[0] < arr.get(i)[0]) {
                    maxi = i;
                }
            }

            time++;
            int cur = -1;
            if (maxi != -1) {
                cur = arr.get(maxi)[1];
                arr.get(maxi)[0]--;
                if (arr.get(maxi)[0] == 0) {
                    arr.remove(maxi);
                }
            }
            processed.add(cur);
        }
        return time;
    }
    /*
        Time Complexity: O(t *nN)
        Space Complexity: O(t)
     */

    /*
        * Approach: Max-Heap
        *
     */
    public int leastInterval_2(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int cnt : count) {
            if (cnt > 0) {
                maxHeap.add(cnt);
            }
        }

        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        while (!maxHeap.isEmpty() || !q.isEmpty()) {
            time++;

            if (maxHeap.isEmpty()) {
                time = q.peek()[1];
            } else {
                int cnt = maxHeap.poll() - 1;
                if (cnt > 0) {
                    q.add(new int[]{cnt, time + n});
                }
            }

            if (!q.isEmpty() && q.peek()[1] == time) {
                maxHeap.add(q.poll()[0]);
            }
        }

        return time;
    }
    /*
        Time Complexity: O(m)
        Space Complexity: O(1)
     */
    /*
        * Approach: Greedy
        *
     */
    public int leastInterval_3(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        Arrays.sort(count);
        int maxf = count[25];
        int idle = (maxf - 1) * n;

        for (int i = 24; i >= 0; i--) {
            idle -= Math.min(maxf - 1, count[i]);
        }
        return Math.max(0, idle) + tasks.length;
    }
    /*
        Time Complexity: O(m)
        Space Complexity: O(1)
     */
    /*
        * Approach: Math
        *
     */
    public int leastInterval_4(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        int maxf = Arrays.stream(count).max().getAsInt();
        int maxCount = 0;
        for (int i : count) {
            if (i == maxf) {
                maxCount++;
            }
        }

        int time = (maxf - 1) * (n + 1) + maxCount;
        return Math.max(tasks.length, time);
    }
    /*
        Time Complexity: O(m)
        Space Complexity: O(1)
     */
}
