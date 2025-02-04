package linkedList;

import java.util.ArrayList;
import java.util.List;

public class removeNthNodeFromList {
    /*
        * Approach: Brute Force
        *
     */
    public ListNode removeNthFromEnd_1(ListNode head, int n) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }

        int removeIndex = nodes.size() - n;
        if (removeIndex == 0) {
            return head.next;
        }

        nodes.get(removeIndex - 1).next = nodes.get(removeIndex).next;
        return head;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Iteration Two Pass
        *
     */
    public ListNode removeNthFromEnd_2(ListNode head, int n) {
        int N = 0;
        ListNode cur = head;
        while (cur != null) {
            N++;
            cur = cur.next;
        }

        int removeIndex = N - n;
        if (removeIndex == 0) {
            return head.next;
        }

        cur = head;
        for (int i = 0; i < N - 1; i++) {
            if ((i + 1) == removeIndex) {
                cur.next = cur.next.next;
                break;
            }
            cur = cur.next;
        }
        return head;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Recursion
        *
     */
    public ListNode rec(ListNode head, int[] n) {
        if (head == null) {
            return null;
        }

        head.next = rec(head.next, n);
        n[0]--;
        if (n[0] == 0) {
            return head.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd_3(ListNode head, int n) {
        return rec(head, new int[]{n});
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Two Pointers
        *
     */
    public ListNode removeNthFromEnd_4(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = head;

        while (n > 0) {
            right = right.next;
            n--;
        }

        while (right != null) {
            left = left.next;
            right = right.next;
        }

        left.next = left.next.next;
        return dummy.next;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
