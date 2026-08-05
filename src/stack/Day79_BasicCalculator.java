package stack;

import java.util.Stack;
public class Day79_BasicCalculator {

    // 1. BRUTE FORCE (USING TWO STACKS)
    // Topic: Stack
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int calculateBrute(String s) {

        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int num = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {

                num = 0;

                while (i < s.length() && Character.isDigit(s.charAt(i))) {

                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }

                numbers.push(num);
                i--;

            } else if (ch == '+' || ch == '-') {

                while (!operators.isEmpty()) {

                    performOperation(numbers, operators);
                }

                operators.push(ch);

            } else if (ch == '(') {

                operators.push(ch);

            } else if (ch == ')') {

                while (operators.peek() != '(') {

                    performOperation(numbers, operators);
                }

                operators.pop();
            }
        }

        while (!operators.isEmpty()) {

            performOperation(numbers, operators);
        }

        return numbers.pop();
    }

    private void performOperation(Stack<Integer> numbers,
                                  Stack<Character> operators) {

        int b = numbers.pop();
        int a = numbers.pop();

        char op = operators.pop();

        if (op == '+') {

            numbers.push(a + b);

        } else {

            numbers.push(a - b);
        }
    }


    // 2. OPTIMAL (STACK)
    // Topic: Stack
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int calculateOptimal(String s) {

        Stack<Integer> stack = new Stack<>();

        int result = 0;
        int number = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {

                number = number * 10 + (ch - '0');

            } else if (ch == '+') {

                result += sign * number;
                number = 0;
                sign = 1;

            } else if (ch == '-') {

                result += sign * number;
                number = 0;
                sign = -1;

            } else if (ch == '(') {

                stack.push(result);
                stack.push(sign);

                result = 0;
                sign = 1;

            } else if (ch == ')') {

                result += sign * number;
                number = 0;

                result *= stack.pop();
                result += stack.pop();
            }
        }

        result += sign * number;

        return result;
    }


    public static void main(String[] args) {

        Day79_BasicCalculator obj =
                new Day79_BasicCalculator();

        String s = "(1+(4+5+2)-3)+(6+8)";

        System.out.println("Brute Force: "
                + obj.calculateBrute(s));

        System.out.println("Optimal: "
                + obj.calculateOptimal(s));
    }
}
