import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  static int N, K;
  static int count;
  static int[] weights;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    count = 0;

    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    weights = new int[N];
    visited = new boolean[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      weights[i] = Integer.parseInt(st.nextToken());
    }

    solve(0, 500);

    System.out.println(count);
  }

  private static void solve(int day, int currentWeight) {

    if (day == N) {
      count++;
      return;
    }

    for (int i = 0; i < N; i++) {
      if (!visited[i]) {

        int nextWeight = currentWeight - K + weights[i];

        if (nextWeight < 500) {
          continue;
        }

        visited[i] = true;
        solve(day + 1, nextWeight);

        visited[i] = false;
      }
    }
  }
}