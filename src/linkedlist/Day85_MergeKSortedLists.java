package linkedlist;

import java.util.PriorityQueue;

public class Day85_MergeKSortedLists {

    static class ListNode {
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

    // 1. BRUTE FORCE
    // Topic: Linked List
    // Time Complexity: O(N × k)
    // Space Complexity: O(1)

    public ListNode mergeKListsBrute(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (true) {
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }

                if (minIndex == -1 || lists[i].val < lists[minIndex].val) {
                    minIndex = i;
                }
            }

            if (minIndex == -1) {
                break;
            }

            tail.next = lists[minIndex];
            tail = tail.next;

            lists[minIndex] = lists[minIndex].next;
        }

        return dummy.next;
    }

    // 2. OPTIMAL (MIN HEAP)
    // Topic: Heap, Linked List
    // Time Complexity: O(N log k)
    // Space Complexity: O(k)

    public ListNode mergeKListsOptimal(ListNode[] lists) {

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;

            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        return dummy.next;
    }

    private static void print(ListNode head) {

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Day85_MergeKSortedLists obj = new Day85_MergeKSortedLists();

        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));

        ListNode[] lists1 = {l1, l2, l3};

        System.out.print("Brute Force : ");
        print(obj.mergeKListsBrute(lists1));

        l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        l3 = new ListNode(2, new ListNode(6));

        ListNode[] lists2 = {l1, l2, l3};

        System.out.print("Optimal : ");
        print(obj.mergeKListsOptimal(lists2));
    }
}