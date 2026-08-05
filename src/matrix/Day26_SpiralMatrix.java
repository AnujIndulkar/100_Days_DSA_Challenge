package matrix;

import java.util.*;

public class Day26_SpiralMatrix {

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n * m)
    // Space Complexity: O(n * m)
    public static List<Integer> bruteForce(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        boolean[][] vis = new boolean[n][m];
        List<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;
        int dir = 0; // 0=right,1=down,2=left,3=up
        for (int count = 0; count < n * m; count++) {
            ans.add(arr[i][j]);
            vis[i][j] = true;
            int ni = i, nj = j;
            if (dir == 0) nj++;
            else if (dir == 1) ni++;
            else if (dir == 2) nj--;
            else ni--;
            if (ni >= 0 && ni < n && nj >= 0 && nj < m && !vis[ni][nj]) {
                i = ni;
                j = nj;
            } else {
                dir = (dir + 1) % 4;

                if (dir == 0) j++;
                else if (dir == 1) i++;
                else if (dir == 2) j--;
                else i--;
            }
        }
        return ans;
    }

    // OPTIMAL APPROACH (Boundary Traversal)
    // Time Complexity: O(n * m)
    // Space Complexity: O(1)
    public static List<Integer> optimal(int[][] arr) {
        List<Integer> ans = new ArrayList<>();
        int top = 0, bottom = arr.length - 1;
        int left = 0, right = arr[0].length - 1;
        while (top <= bottom && left <= right) {
            // left to right
            for (int k = left; k <= right; k++) {
                ans.add(arr[top][k]);
            }
            top++;
            // top to bottom
            for (int k = top; k <= bottom; k++) {
                ans.add(arr[k][right]);
            }
            right--;
            // right to left
            if (top <= bottom) {
                for (int k = right; k >= left; k--) {
                    ans.add(arr[bottom][k]);
                }
                bottom--;
            }
            // bottom to top
            if (left <= right) {
                for (int k = bottom; k >= top; k--) {
                    ans.add(arr[k][left]);
                }
                left++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter rows: ");
        int n = sc.nextInt();
        System.out.print("Enter columns: ");
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        System.out.println("Enter matrix:");
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                arr[x][y] = sc.nextInt();
            }
        }
        // List<Integer> res = bruteForce(arr);
        List<Integer> res = optimal(arr);
        System.out.println("Spiral Order: " + res);
    }
}
