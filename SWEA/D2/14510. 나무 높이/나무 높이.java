import java.util.Scanner;

public class Solution {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] trees = new int[N];

			int max_height = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				trees[i] = sc.nextInt();
				max_height = Math.max(max_height, trees[i]);
			}

			int ans = Integer.MAX_VALUE;
			ans = water(trees, N, max_height);
			ans = Math.min(ans, water(trees, N, max_height+1));
            
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static int water(int[] trees, int N, int max_height) {
		int days = 0, odd = 0, even = 0;

		for (int i = 0; i < N; i++) {
			even += (max_height - trees[i]) / 2;
			odd += (max_height - trees[i]) % 2;
		}

		int mins = Math.min(odd, even);
		odd -= mins;
		even -= mins;
		
		days += mins *2;
		
		if(even == 0) {
			days += 2*odd-1;
		}

		if(odd == 0) {
			days += even + 1 +  (even - 1)/3;
		}

		return days;
	}

}

