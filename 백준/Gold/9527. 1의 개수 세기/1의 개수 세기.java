import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long A, B;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        long result = countBits(B) - countBits(A - 1);
        System.out.println(result);
    }

    /*
     * 사이클이란?
     * 각 비트 위치에서 0과 1이 반복되는 패턴
     * 0번 비트: 0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1
     * 1번 비트: 0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1
     * 2번 비트: 0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1
     * 3번 비트: 0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1
     * 
     * N번 비트의 사이클 길이 = 2^(N+1)
     * 각 사이클에서 1의 개수 = 2^N
     */

    static long countBits(long n) {
        if (n <= 0) return 0;
        
        long count = 0;
        long powerOf2 = 1;
        
        while (powerOf2 <= n) {
            // 현재 비트 위치에서의 사이클 크기
            long cycleLength = powerOf2 * 2;
            
            // 완전한 사이클의 개수
            long completeCycles = (n + 1) / cycleLength;
            
            // 완전한 사이클에서의 1비트 개수
            count += completeCycles * powerOf2;
            
            // 마지막 불완전한 사이클에서의 1비트 개수
            long remainder = (n + 1) % cycleLength;
            if (remainder > powerOf2) {
                count += remainder - powerOf2;
            }
            
            powerOf2 <<= 1; // 다음 비트 위치로
        }
        
        return count;
    }
}