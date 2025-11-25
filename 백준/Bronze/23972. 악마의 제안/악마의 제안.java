import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    st = new StringTokenizer(br.readLine());
    long K = Long.parseLong(st.nextToken());
    long N = Long.parseLong(st.nextToken());

    if(N == 1) {
      System.out.println(-1);
      return;
    }

    System.out.println((K*N+N-2)/(N-1));
  }
}
