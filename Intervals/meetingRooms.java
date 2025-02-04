package Intervals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Interval {
    public int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class meetingRooms {
    /*
     * Approach: Brute Force
     *
     */
    public boolean canAttendMeetings_1(List<Interval> intervals) {
        int n = intervals.size();
        for (int i = 0; i < n; i++) {
            Interval A = intervals.get(i);
            for (int j = i + 1; j < n; j++) {
                Interval B = intervals.get(j);
                if (Math.min(A.end, B.end) > Math.max(A.start, B.start)) {
                    return false;
                }
            }
        }
        return true;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Sorting
        *
     */
    public boolean canAttendMeetings_2(List<Interval> intervals) {
        Collections.sort(intervals, Comparator.comparingInt(i -> i.start));

        for (int i = 1; i < intervals.size(); i++) {
            Interval i1 = intervals.get(i - 1);
            Interval i2 = intervals.get(i);

            if (i1.end > i2.start) {
                return false;
            }
        }

        return true;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
}
