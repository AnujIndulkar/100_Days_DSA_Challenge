package Arrays;

import java.util.Scanner;

public class Day07_BestTimeToBuyAndSellStock {
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] prices = new int[n];
        for(int i = 0; i < n; i++){
            prices[i] = sc.nextInt();
        }
//        System.out.print(solution(prices));
        System.out.print(solution1(prices));
    }
    public static int solution(int [] prices){
        // Brute Force Approach
        // Time Complexity - O(n^2)
        // Space Complexity - O(1)
        int n = prices.length;
        int maxProfit = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
    public static int solution1(int [] prices){
        // Optimal Approach - Greedy Algorithm
        // Pattern- One-pass traversal
        // Time Complexity - O(n)
        // Space Complexity - O(1)
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return maxProfit;
    }
}
