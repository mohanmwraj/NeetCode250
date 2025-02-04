package linkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class mergeKSortedLists {
    /*
        * Approach: Brute Force
        *
     */
    public ListNode mergeKLists_1(ListNode[] lists) {
        List<Integer> nodes = new ArrayList<>();
        for (ListNode lst : lists) {
            while (lst != null) {
                nodes.add(lst.val);
                lst = lst.next;
            }
        }
        Collections.sort(nodes);

        ListNode res = new ListNode(0);
        ListNode cur = res;
        for (int node : nodes) {
            cur.next = new ListNode(node);
            cur = cur.next;
        }
        return res.next;
    }
    /*
        Time Complexity: O(n log n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Iteration
        *
     */
    public ListNode mergeKLists_2(ListNode[] lists) {
        ListNode res = new ListNode(0);
        ListNode cur = res;

        while (true) {
            int minNode = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (minNode == -1 || lists[minNode].val > lists[i].val) {
                    minNode = i;
                }
            }

            if (minNode == -1) {
                break;
            }
            cur.next = lists[minNode];
            lists[minNode] = lists[minNode].next;
            cur = cur.next;
        }

        return res.next;
    }
    /*
        Time Complexity: O(n * k)
        Space Complexity: O(n)
     */
    /*
        * Approach: Merge Lists One By One
        *
     */
    public ListNode mergeKLists_3(ListNode[] lists) {
        if (lists.length == 0) return null;

        for (int i = 1; i < lists.length; i++) {
            lists[i] = merge(lists[i], lists[i - 1]);
        }
        return lists[lists.length - 1];
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        if (l1 != null) {
            curr.next = l1;
        } else {
            curr.next = l2;
        }

        return dummy.next;
    }
    /*
        Time Complexity: O(n * k)
        Space Complexity: O(1)
     */
    /*
        * Approach: Heap
        *
     */
    public ListNode mergeKLists_4(ListNode[] lists) {
        if (lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            cur.next = node;
            cur = cur.next;

            node = node.next;
            if (node != null) {
                minHeap.offer(node);
            }
        }
        return res.next;
    }
    /*
        Time Complexity: O(n * log k)
        Space Complexity: O(k)
     */
    /*
        * Approach: Divide and Conquer (Recursion)
        *
     */
    public ListNode mergeKLists_5(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return divide(lists, 0, lists.length - 1);
    }

    private ListNode divide(ListNode[] lists, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return lists[l];
        }
        int mid = l + (r - l) / 2;
        ListNode left = divide(lists, l, mid);
        ListNode right = divide(lists, mid + 1, r);
        return conquer(left, right);
    }

    private ListNode conquer(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        if (l1 != null) {
            curr.next = l1;
        } else {
            curr.next = l2;
        }

        return dummy.next;
    }
    /*
        Time Complexity: O(n * log k)
        Space Complexity: O(log k)
     */
    /*
        * Approach: Divide and Conquer Iteration
        *
     */
    public ListNode mergeKLists_6(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        while (lists.length > 1) {
            List<ListNode> mergedLists = new ArrayList<>();
            for (int i = 0; i < lists.length; i += 2) {
                ListNode l1 = lists[i];
                ListNode l2 = (i + 1) < lists.length ? lists[i + 1] : null;
                mergedLists.add(mergeList(l1, l2));
            }
            lists = mergedLists.toArray(new ListNode[0]);
        }
        return lists[0];
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        return dummy.next;
    }
    /*
        Time Complexity: O(n * log k)
        Space Complexity: O(k)
     */
}
