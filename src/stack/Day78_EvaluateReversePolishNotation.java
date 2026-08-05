package stack;

import java.util.Stack;

public class Day78_EvaluateReversePolishNotation {

    // 1. BRUTE FORCE (RECURSION)
    // Topic: Recursion
    // Time Complexity: O(n²)
    // Space Complexity: O(n)

    public int evalRPNBrute(String[] tokens) {
        return evaluate(tokens, tokens.length - 1);
    }

    private int evaluate(String[] tokens, int index) {
        Stack<Integer> values = new Stack<>();
        for (int i = 0; i <= index; i++) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = values.pop();
                int a = values.pop();
                switch (token) {
                    case "+": values.push(a + b);
                        break;
                    case "-": values.push(a - b);
                        break;
                    case "*": values.push(a * b);
                        break;
                    case "/": values.push(a / b);
                        break;
                }
            } else {
                values.push(Integer.parseInt(token));
            }
        }
        return values.pop();
    }


    // 2. OPTIMAL (STACK)
    // Topic: Stack
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int evalRPNOptimal(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a + b);
            } else if (token.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a * b);
            } else if (token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }


    public static void main(String[] args) {
        Day78_EvaluateReversePolishNotation obj = new Day78_EvaluateReversePolishNotation();
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println("Brute Force: " + obj.evalRPNBrute(tokens));
        System.out.println("Optimal: " + obj.evalRPNOptimal(tokens));
    }
}
