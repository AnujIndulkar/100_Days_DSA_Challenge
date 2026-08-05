package Arrays;

import java.util.*;

public class Day31_IntersectionOfTwoArrays {

    // TOPIC: Intersection of Two Arrays

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n * m)
    // Space Complexity: O(n)
    public static int[] bruteForce(int[] nums1, int[] nums2) {
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    result.add(nums1[i]);
                }
            }
        }

        int[] ans = new int[result.size()];
        int i = 0;
        for (int num : result) {
            ans[i++] = num;
        }

        return ans;
    }

    // OPTIMAL APPROACH (HashSet)
    // Time Complexity: O(n + m)
    // Space Complexity: O(n)
    public static int[] optimal(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        // store elements of nums1
        for (int num : nums1) {
            set1.add(num);
        }

        // check intersection
        for (int num : nums2) {
            if (set1.contains(num)) {
                result.add(num);
            }
        }

        // convert set to array
        int[] ans = new int[result.size()];
        int i = 0;
        for (int num : result) {
            ans[i++] = num;
        }

        return ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input first array
        System.out.print("Enter size of first array: ");
        int n1 = sc.nextInt();
        int[] nums1 = new int[n1];

        System.out.print("Enter elements of first array: ");
        for (int i = 0; i < n1; i++) {
            nums1[i] = sc.nextInt();
        }

        // input second array
        System.out.print("Enter size of second array: ");
        int n2 = sc.nextInt();
        int[] nums2 = new int[n2];

        System.out.print("Enter elements of second array: ");
        for (int i = 0; i < n2; i++) {
            nums2[i] = sc.nextInt();
        }

        // int[] res = bruteForce(nums1, nums2); // brute force
        int[] res = optimal(nums1, nums2); // optimal

        System.out.println("Intersection of arrays:");
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
