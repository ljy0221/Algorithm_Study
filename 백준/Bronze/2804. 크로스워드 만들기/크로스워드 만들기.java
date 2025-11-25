import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    st = new StringTokenizer(br.readLine());

    String A = st.nextToken();
    String B = st.nextToken();
    int N = A.length();
    int M = B.length();
    char[][] map = new char[M][N];

    for (int i = 0; i < M; i++) {
      Arrays.fill(map[i], '.');
    }

    int crossIndexA = -1;
    int crossIndexB = -1;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if(A.charAt(i) == B.charAt(j)) {
          crossIndexA = i;
          crossIndexB = j;
          break;
        }
      }
      if(crossIndexA != -1 && crossIndexB != -1) {
        break;
      }
    }

    map[crossIndexB] = A.toCharArray();
    for (int i = 0; i < M; i++) {
      map[i][crossIndexA] = B.charAt(i);
    }

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(map[i][j]);
      }
      System.out.println();
    }
  }
}
