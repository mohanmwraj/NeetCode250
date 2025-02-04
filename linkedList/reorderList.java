package linkedList;

import java.util.ArrayList;
import java.util.List;

/*
    [0, 1, 2, 3, 4, 5, 6]

    Reorder the nodes of the linked list to be in the following order:
    [0, 6, 1, 5, 2, 4, 3]

    [0, n-1, 1, n-2, 2, n-3, ...]
 */
public class reorderList {
    /*
        * Approach: Brute Force
        *
     */
    public void reorderList_1(ListNode head) {
        if (head == null) {
            return;
        }

        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }

        int i = 0, j = nodes.size() - 1;
        while (i < j) {
            nodes.get(i).next = nodes.get(j);
            i++;
            if (i >= j) {
                break;
            }
            nodes.get(j).next = nodes.get(i);
            j--;
        }

        nodes.get(i).next = null;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Recursion
        *
     */
    public void reorderList_2(ListNode head) {
        head = rec(head, head.next);
    }

    private ListNode rec(ListNode root, ListNode cur) {
        if (cur == null) {
            return root;
        }
        root = rec(root, cur.next);
        if (root == null) {
            return null;
        }
        ListNode tmp = null;
        if (root == cur || root.next == cur) {
            cur.next = null;
        } else {
            tmp = root.next;
            root.next = cur;
            cur.next = tmp;
        }
        return tmp;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Reverse and Merge
        *
     */
    public void reorderList_3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        ListNode prev = slow.next = null;
        while (second != null) {
            ListNode tmp = second.next;
            second.next = prev;
            prev = second;
            second = tmp;
        }

        ListNode first = head;
        second = prev;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
