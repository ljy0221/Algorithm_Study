import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 배열의 마지막부터 시작
 * 1. 왼쪽 인덱스의 높이가 내 높이보다 높은지 확인
 * 2. 높으면 해당 인덱스 입력
 * 3. 낮으면 1번으로
 * 
 */

public class Main {
//	시간초과: O(N^2)
//	static int N;
//	static int[] towers;
//	static int[] receivers;
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		N = Integer.parseInt(br.readLine());
//		towers = new int[N];
//		receivers = new int[N];
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for(int i = 0; i < N; i++) {
//			towers[i] = Integer.parseInt(st.nextToken());
//		}
//		
//		for(int i = N-1; i > 0; i--) {
//			for(int j = i; j >= 0; j--) {
//				if(towers[i] < towers[j]) {
//					receivers[i] = j+1;
//					break;
//				}
//			}
//		}
//		
//		for(int num : receivers) {
//			System.out.print(num + " ");
//		}
//	}
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Stack<Integer> stack = new Stack<>(); // 인덱스만 저장
        int[] towers = new int[N + 1]; // 1-based 인덱스 사용
        int[] result = new int[N + 1];
        
        for(int i = 1; i <= N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
            
            // 현재 탑보다 낮은 탑들 제거
            while(!stack.isEmpty() && towers[stack.peek()] < towers[i]) {
                stack.pop();
            }
            
            // 수신탑 결정
            result[i] = stack.isEmpty() ? 0 : stack.peek();
            
            // 현재 탑 추가
            stack.push(i);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }
        
        System.out.println(sb.toString().trim());
    }
}
