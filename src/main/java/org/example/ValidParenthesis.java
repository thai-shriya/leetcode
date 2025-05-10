package org.example;
import java.util.Stack;

public class ValidParenthesis {
    /*Thought process
Problem asks for maintaing the correct order of brackets with open and close of same type

So, in order to keep track of the order, I used stack.
Insert any opening bracket into the stack and, when we encounter any closing bracket, we check with the top element of the stack.
If they match, then we have the pair in right order. So, we pop the top of the stack and repeat the same steps again.
If in any case, they do not match, then we return false right away.

After the entire string is processed, at the end we check if the stack is empty. If it is empty, then it means that we have all the pairs in right order, so we return true.
If it is not empty, then we return false.

The string is processed once, so the time complexity is O(n) and we make use of stack with space complexity of O(n)
*/
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stack.push(s.charAt(i));
            else if(s.charAt(i) == ')' && !stack.isEmpty() && stack.peek()=='(')
                stack.pop();
            else if(s.charAt(i) == ']' && !stack.isEmpty() && stack.peek()=='[')
                stack.pop();
            else if(s.charAt(i) == '}' && !stack.isEmpty() && stack.peek()=='{')
                stack.pop();
            else
                return false;

        }

        if(stack.isEmpty())
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        ValidParenthesis validator = new ValidParenthesis();

        String[] testInputs = {
                "()",
                "()[]{}",
                "(]",
                "([)]",
                "{[]}",
                "",
                "[",
                "]",
                "((()))",
                "{[()]}"
        };

        for (String input : testInputs) {
            System.out.println("Input: " + input + " -> Valid: " + validator.isValid(input));
        }
    }
}
