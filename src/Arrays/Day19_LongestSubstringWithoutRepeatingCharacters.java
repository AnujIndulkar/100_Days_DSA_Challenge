package Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Day19_LongestSubstringWithoutRepeatingCharacters {

    // BRUTE FORCE APPROACH
    // Idea: Generate all substrings and check duplicates using HashSet
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)

    public static int bruteForce(String s) {
        int n = s.length();
        int maxLength = 0;
        for(int i = 0; i < n; i++) {
            HashSet<Character> set = new HashSet<>();
            for(int j = i; j < n; j++) {
                if(set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }


    // OPTIMAL APPROACH
    // Idea: Sliding Window + HashSet
    // Move right pointer to expand window
    // Move left pointer when duplicate appears
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public static int optimal(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        for(int right = 0; right < s.length(); right++) {
            while(set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string: ");
        String s = sc.nextLine();

//        System.out.println("Brute Force Answer: " + bruteForce(s));
        System.out.println("Optimal Answer: " + optimal(s));
    }
}
