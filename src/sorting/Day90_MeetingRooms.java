package sorting;

import java.util.Arrays;

public class Day90_MeetingRooms {

    // --------------------------------------------------
    // 1. BRUTE FORCE
    // Topic: Array, Sorting
    // Time Complexity: O(n²)
    // Space Complexity: O(1)
    // --------------------------------------------------

    public boolean canAttendMeetingsBrute(int[][] intervals) {

        for (int i = 0; i < intervals.length; i++) {

            for (int j = i + 1; j < intervals.length; j++) {

                if (intervals[i][0] < intervals[j][1] && intervals[j][0] < intervals[i][1]) {

                    return false;
                }
            }
        }

        return true;
    }


    // --------------------------------------------------
    // 2. OPTIMAL (SORTING)
    // Topic: Array, Sorting
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)
    // --------------------------------------------------

    public boolean canAttendMeetingsOptimal(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < intervals[i - 1][1]) {

                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {

        Day90_MeetingRooms obj = new Day90_MeetingRooms();

        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        System.out.println("Brute Force : " + obj.canAttendMeetingsBrute(intervals));

        int[][] intervals2 = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        System.out.println("Optimal : " + obj.canAttendMeetingsOptimal(intervals2));
    }
}
