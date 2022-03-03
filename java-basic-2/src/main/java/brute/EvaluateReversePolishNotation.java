package brute;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Runtime: 8 ms, faster than 62.67% of Java online submissions for Evaluate Reverse Polish Notation.
 * Memory Usage: 44.1 MB, less than 34.37% of Java online submissions for Evaluate Reverse Polish Notation.
 */
public class EvaluateReversePolishNotation {
    public static final String OP_ADD = "+";
    public static final String OP_SUB = "-";
    public static final String OP_MUL = "*";
    public static final String OP_DIV = "/";

    public int evalRPN(String[] tokens) {
        var stack = new Stack<Integer>();

        for(var token: tokens) {
            switch(token) {
                case OP_ADD:
                    int operand1 = stack.pop();
                    int operand2 = stack.pop();
                    int result = operand2 + operand1;
                    stack.push(result);
                    break;
                case OP_SUB:
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    result = operand2 - operand1;
                    stack.push(result);
                    break;
                case OP_MUL:
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    result = operand2 * operand1;
                    stack.push(result);
                    break;
                case OP_DIV:
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    result = operand2 / operand1;
                    stack.push(result);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
