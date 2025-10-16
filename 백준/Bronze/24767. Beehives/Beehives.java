import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static double d;
    static int N;
    static Pos[] pos;

    public static void main(String[] args) throws IOException {
        
        double d2;

        while (true) {
            st = new StringTokenizer(br.readLine());
    
            d = Double.parseDouble(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if(d == 0 && N == 0) break;
            
            d2 = d*d;
            pos = new Pos[N];

            int a = 0;
            int b = 0;

    
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                double x, y;

                x = Double.parseDouble(st.nextToken());
                y = Double.parseDouble(st.nextToken());

                pos[i] = new Pos(x, y);
            }

            boolean[] isSour = new boolean[N];

            for (int i = 0; i < N-1; i++) {
                for (int j = i+1; j < N; j++) {
                    double dist = Math.pow(pos[i].x - pos[j].x, 2) + Math.pow(pos[i].y - pos[j].y, 2);

                    if(dist < d2) {
                        isSour[i] = true;
                        isSour[j] = true;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                if(isSour[i]) {
                    a++;
                } else {
                    b++;
                }
            }
    
            sb.append(a).append(" sour, ").append(b).append(" sweet").append('\n');
            
        }

        System.out.print(sb);
    }

    private static class Pos {
        double x, y;

        public Pos(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }
}
