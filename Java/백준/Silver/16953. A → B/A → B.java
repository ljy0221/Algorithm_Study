import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * A를 B로 바꾸는 연산
 * A*2 / 뒤에 1더하기 -  두개의 연산만 사용
 * 1. B가 2로 나누어지는지 확인
 * 2. 나누어지면 나누고 다시 1번으로
 * 3. 안 나누어지면 1 없에기
 */

public class Main {
	static int A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		
		int count = 1;
		while(B > A) {
			if(B % 2 == 0) {
				B /= 2;
			} else if(B % 10 == 1){
				B /= 10;
			} else {
				System.out.println(-1);
				return;
			}
			count++;
		}
		
		if(B == A) {
			System.out.println(count);
		} else {
			System.out.println(-1);
		}
	}
}
