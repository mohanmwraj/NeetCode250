package linkedList;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class reverseLinkedList {
    /*
         * Approach: Recursion
         *
     */
    public ListNode reverseList_1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newHead = head;
        if (head.next != null) {
            newHead = reverseList_1(head.next);
            head.next.next = head;
        }
        head.next = null;

        return newHead;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Iteration
        *
     */
    public ListNode reverseList_2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
