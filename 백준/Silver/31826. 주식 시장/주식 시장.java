import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int N = Integer.parseInt(br.readLine());

    TreeMap<Integer, Integer> hrx = new TreeMap<>();

    int currentPrice = 10000;

    for (int tc = 0; tc < N; tc++) {
      st = new StringTokenizer(br.readLine());
      int p = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int f = Integer.parseInt(st.nextToken());

      if (f == -1) { // sell
        if (hrx.getOrDefault(p, 0) > 0) {
          currentPrice = p;

        }
        hrx.put(p, hrx.getOrDefault(p, 0) - x);
      } else { // buy
        if(hrx.getOrDefault(p, 0) < 0) {
          currentPrice = p;
        }

        hrx.put(p, hrx.getOrDefault(p, 0) + x);
      }
    }

    System.out.println(currentPrice);
  }
}
