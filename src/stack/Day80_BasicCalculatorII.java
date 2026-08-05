package stack;

import java.util.Stack;

public class Day80_BasicCalculatorII {

    // 1. BRUTE FORCE (TWO STACKS)
    // Topic: Stack
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int calculateBrute(String s) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            }

            if (Character.isDigit(ch)) {
                num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                numbers.push(num);
                i--;

            } else {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    performOperation(numbers, operators);
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            performOperation(numbers, operators);
        }

        return numbers.pop();
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        return 2;
    }

    private void performOperation(Stack<Integer> numbers, Stack<Character> operators) {
        int b = numbers.pop();
        int a = numbers.pop();

        char op = operators.pop();
        switch (op) {
            case '+': numbers.push(a + b);
                break;

            case '-': numbers.push(a - b);
                break;

            case '*': numbers.push(a * b);
                break;

            case '/': numbers.push(a / b);
                break;
        }
    }


    // 2. OPTIMAL (ONE STACK)
    // Topic: Stack
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int calculateOptimal(String s) {
        Stack<Integer> stack = new Stack<>();

        int number = 0;
        char operation = '+';

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            }

            if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {
                switch (operation) {

                    case '+': stack.push(number);
                        break;

                    case '-': stack.push(-number);
                        break;

                    case '*': stack.push(stack.pop() * number);
                        break;

                    case '/': stack.push(stack.pop() / number);
                        break;
                }
                operation = ch;
                number = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }


    public static void main(String[] args) {
        Day80_BasicCalculatorII obj = new Day80_BasicCalculatorII();

        String s = "3+2*2";
        System.out.println("Brute Force: " + obj.calculateBrute(s));

        System.out.println("Optimal: " + obj.calculateOptimal(s));
    }
}
