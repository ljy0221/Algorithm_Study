import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        String input = br.readLine();

        int starIndex = -1;
        int baseSum = 0;
        
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            
            if (ch == '*') {
                starIndex = i;
            } else {
                int digit = ch - '0';
                baseSum += (i % 2 == 1) ? digit * 3 : digit;
            }
        }
        
        // 0~9까지 시도하여 유효한 숫자 찾기
        for (int candidate = 0; candidate <= 9; candidate++) {
            int totalSum = baseSum;
            
            // '*' 위치에 후보 숫자 적용
            if (starIndex % 2 == 1) {
                totalSum += candidate * 3;
            } else {
                totalSum += candidate;
            }
            
            
            if (totalSum % 10 == 0) {
                sb.append(candidate).append('\n');
                break;
            }
        }
        
        System.out.print(sb);
    }
}