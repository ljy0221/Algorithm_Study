import java.io.*;
import java.util.*;

public class Main {
    
    static int getPriority(char op) {
        if (op == '*' || op == '/') {
            return 2;
        } else if (op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String infix = br.readLine();

        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);

            if (Character.isLetter(currentChar)) {
                postfix.append(currentChar);
            } 
            else if (currentChar == '(') {
                stack.push(currentChar);
            } 
            else if (currentChar == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            } 
            else { 
                while (!stack.isEmpty() && getPriority(currentChar) <= getPriority(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(currentChar);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        System.out.println(postfix.toString());
    }
}