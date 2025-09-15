import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 오르막 수 - 자리가 오름차순, 같아도 증가로 본다
 * 선형- 다음 숫자가 현재 숫자와 같거나 크면 계속진행
 * 작으면 종료
 * 길이가 n인 증가수열 1: 10(0~9), 2: 55, 3: 220(000 ~ 999)
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] climbNumbers = new int[N+1][10];
		
		// basis
		for(int i = 0; i < 10; i++) {
			climbNumbers[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j < 10; j++) {

                for (int k = 0; k <= j; k++) {
                	climbNumbers[i][j] = (climbNumbers[i][j] + climbNumbers[i-1][k]) % 10007;
                }
			}
		}
		
		int result = 0;
	    for (int j = 0; j < 10; j++) {
	        result = (result + climbNumbers[N][j]) % 10007;
	    }
		
		System.out.println(result);
	}
}
