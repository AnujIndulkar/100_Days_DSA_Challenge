package heap;
import java.util.*;


public class Day58_MergeKSortedLists {

    // Definition for singly-linked list
    static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 1. BRUTE FORCE
    // Time Complexity: O(N log N)
    // Space Complexity: O(N)

    public ListNode mergeKListsBrute(ListNode[] lists) {

        ArrayList<Integer> values = new ArrayList<>();

        // Store all values
        for (ListNode node : lists) {

            while (node != null) {
                values.add(node.val);
                node = node.next;
            }
        }

        // Sort values
        Collections.sort(values);

        // Create new sorted list
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        for (int value : values) {

            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    // 2. OPTIMAL (MIN HEAP)
    // Time Complexity: O(N log k)
    // Space Complexity: O(k)

    public ListNode mergeKListsOptimal(ListNode[] lists) {

        PriorityQueue<ListNode> minHeap =
                new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add first node of each list
        for (ListNode node : lists) {

            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {

            ListNode smallest = minHeap.poll();

            tail.next = smallest;
            tail = tail.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }

    // Print Linked List
    public void printList(ListNode head) {

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Day58_MergeKSortedLists obj =
                new Day58_MergeKSortedLists();

        // List 1: 1 -> 4 -> 5
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        // List 2: 1 -> 3 -> 4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        // List 3: 2 -> 6
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};

        System.out.print("Brute Force: ");
        obj.printList(obj.mergeKListsBrute(lists));

        System.out.print("Optimal: ");
        obj.printList(obj.mergeKListsOptimal(lists));
    }
}
