package linkedList;

import java.util.HashSet;

public class linkedListCycle {
    /*
        * Approach: Hash Set
        *
     */
    public boolean hasCycle_1(ListNode head) {
        HashSet<ListNode> seen = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (seen.contains(cur)) {
                return true;
            }
            seen.add(cur);
            cur = cur.next;
        }
        return false;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Fast and Slow Pointers
        *
     */
    public boolean hasCycle_2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
