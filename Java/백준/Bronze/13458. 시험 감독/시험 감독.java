import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, A, B;
	static int[] rooms;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		rooms = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			rooms[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		long count = N;
		for (int i = 0; i < N; i++) {
			rooms[i] -= A;

			if (rooms[i] > 0) {
				if (rooms[i] % B != 0) {
					count++;
				}
				count += rooms[i] / B;
			}
		}

		System.out.println(count);
	}
}
