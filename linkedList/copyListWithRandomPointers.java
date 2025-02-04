package linkedList;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class copyListWithRandomPointers {
    /*
        * Approach: Recursion
        *
     */
    HashMap<Node, Node> map = new HashMap<>();

    public Node copyRandomList_1(Node head) {
        if (head == null) return null;
        if (map.containsKey(head)) return map.get(head);

        Node copy = new Node(head.val);
        map.put(head, copy);
        copy.next = copyRandomList_1(head.next);
        copy.random = map.get(head.random);
        return copy;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Hash Map (Two Pass)
        *
     */
    public Node copyRandomList_2(Node head) {
        Map<Node, Node> oldToCopy = new HashMap<>();
        oldToCopy.put(null, null);

        Node cur = head;
        while (cur != null) {
            Node copy = new Node(cur.val);
            oldToCopy.put(cur, copy);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node copy = oldToCopy.get(cur);
            copy.next = oldToCopy.get(cur.next);
            copy.random = oldToCopy.get(cur.random);
            cur = cur.next;
        }

        return oldToCopy.get(head);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Hash Map - One Pass
        *
     */
    public Node copyRandomList_3(Node head) {
        HashMap<Node, Node> oldToCopy = new HashMap<>();
        oldToCopy.put(null, null);

        Node cur = head;
        while (cur != null) {
            if (!oldToCopy.containsKey(cur)) {
                oldToCopy.put(cur, new Node(0));
            }
            oldToCopy.get(cur).val = cur.val;

            if (!oldToCopy.containsKey(cur.next)) {
                oldToCopy.put(cur.next, new Node(0));
            }
            oldToCopy.get(cur).next = oldToCopy.get(cur.next);

            if (!oldToCopy.containsKey(cur.random)) {
                oldToCopy.put(cur.random, new Node(0));
            }
            oldToCopy.get(cur).random = oldToCopy.get(cur.random);
            cur = cur.next;
        }
        return oldToCopy.get(head);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Space Optimized - I
        *
     */
    public Node copyRandomList_4(Node head) {
        if (head == null) {
            return null;
        }

        Node l1 = head;
        while (l1 != null) {
            Node l2 = new Node(l1.val);
            l2.next = l1.next;
            l1.next = l2;
            l1 = l2.next;
        }

        Node newHead = head.next;

        l1 = head;
        while (l1 != null) {
            if (l1.random != null) {
                l1.next.random = l1.random.next;
            }
            l1 = l1.next.next;
        }

        l1 = head;
        while (l1 != null) {
            Node l2 = l1.next;
            l1.next = l2.next;
            if (l2.next != null) {
                l2.next = l2.next.next;
            }
            l1 = l1.next;
        }

        return newHead;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Space Optimized - II
        *
     */
    public Node copyRandomList_5(Node head) {
        if (head == null) {
            return null;
        }

        Node l1 = head;
        while (l1 != null) {
            Node l2 = new Node(l1.val);
            l2.next = l1.random;
            l1.random = l2;
            l1 = l1.next;
        }

        Node newHead = head.random;

        l1 = head;
        while (l1 != null) {
            Node l2 = l1.random;
            l2.random = (l2.next != null) ? l2.next.random : null;
            l1 = l1.next;
        }

        l1 = head;
        while (l1 != null) {
            Node l2 = l1.random;
            l1.random = l2.next;
            l2.next = (l1.next != null) ? l1.next.random : null;
            l1 = l1.next;
        }

        return newHead;
    }

    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
