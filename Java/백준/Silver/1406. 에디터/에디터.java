import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String input = br.readLine();
        int M = Integer.parseInt(br.readLine());
        
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        
        for(int i = 0; i < input.length(); i++) {
            left.push(input.charAt(i));
        }
        
        while(M-- > 0) {
            String line = br.readLine();
            char op = line.charAt(0);
            
            switch(op) {
            case 'L':
                if(!left.isEmpty()) {
                    right.push(left.pop());
                }
                break;
            case 'D':
                if(!right.isEmpty()) {
                    left.push(right.pop());
                }
                break;
            case 'B':
                if(!left.isEmpty()) {
                    left.pop();
                }
                break;
            case 'P':
                char ch = line.charAt(2); // "P x" 형태이므로 인덱스 2
                left.push(ch);
                break;
            }
        }
        
        StringBuilder result = new StringBuilder();
        while(!left.isEmpty()) {
            result.append(left.pop());
        }
        result.reverse();
        while(!right.isEmpty()) {
            result.append(right.pop());
        }
        
        System.out.println(result);
    }
}