import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());

    long cnt = 0;

    for (int i = 1; i <= n; i++) {
      cnt += check(i, d);
    }

    System.out.println(cnt);
  }

  private static int check(int num, int digit) {
    if(num == 0) {
      return digit == 0 ? 1 : 0;
    }

    int cnt = 0;
    int cur = num;

    while (cur > 0) {
      int remain = cur % 10;

      if(remain == digit) {
        cnt++;
      }
      cur /= 10;
    }

    return cnt;
  }
}
