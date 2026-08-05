package heap;

import java.util.*;

public class Day57_MeetingRoomsII {

    // 1. BRUTE FORCE
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)

    public int minMeetingRoomsBrute(int[][] intervals) {

        int n = intervals.length;
        int maxRooms = 0;

        for (int i = 0; i < n; i++) {

            int rooms = 1;

            for (int j = i + 1; j < n; j++) {

                // Overlapping meetings
                if (intervals[i][1] > intervals[j][0] &&
                        intervals[i][0] < intervals[j][1]) {

                    rooms++;
                }
            }

            maxRooms = Math.max(maxRooms, rooms);
        }

        return maxRooms;
    }

    // 2. OPTIMAL (MIN HEAP)
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)

    public int minMeetingRoomsOptimal(int[][] intervals) {

        if (intervals.length == 0) return 0;

        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add first meeting end time
        minHeap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {

            // Reuse room
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }

            // Add current meeting end time
            minHeap.offer(intervals[i][1]);
        }

        return minHeap.size();
    }

    public static void main(String[] args) {

        Day57_MeetingRoomsII obj =
                new Day57_MeetingRoomsII();

        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        System.out.println("Brute Force: " +
                obj.minMeetingRoomsBrute(intervals));

        System.out.println("Optimal: " +
                obj.minMeetingRoomsOptimal(intervals));
    }
}
