import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    private static boolean isSmaller(long h1, long w1, long h2, long w2) {
        long d1_sq = h1 * h1 + w1 * w1;
        long d2_sq = h2 * h2 + w2 * w2;

        if (d1_sq < d2_sq) {
            return true;
        } else if (d1_sq == d2_sq) {
            return h1 <= h2;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;

        final int MAX_SIZE = 150; 
        
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (h == 0 && w == 0) {
                break;
            }

            int best_h = -1;
            int best_w = -1;
            
            for (int ch = 1; ch <= MAX_SIZE; ch++) {
                for (int cw = ch + 1; cw <= MAX_SIZE; cw++) {
                    
                    boolean is_larger = !isSmaller(ch, cw, h, w);

                    if (is_larger) {
                        if (best_h == -1 || isSmaller(ch, cw, best_h, best_w)) {
                            best_h = ch;
                            best_w = cw;
                        }
                    }
                }
            }
            
            sb.append(best_h).append(" ").append(best_w).append('\n');
        }

        System.out.print(sb.toString());
    }
}