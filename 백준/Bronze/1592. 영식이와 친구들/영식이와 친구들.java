import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());

    int[] count = new int[N];

    int turn = 0;
    int player = 0;
    count[player]++;
    while (true) {
      if(count[player] >= M) break;

      player = (player+L)%N;
      count[player]++;
      turn++;
    }

    System.out.println(turn);
  }
}
