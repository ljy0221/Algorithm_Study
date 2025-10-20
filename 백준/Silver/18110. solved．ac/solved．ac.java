import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int div = (int)Math.round((double)N *0.15);
        List<Integer> difficult = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            difficult.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(difficult);

        int sum = 0;

        for (int i = div; i < N-div; i++) {
            sum += difficult.get(i);
        }

        // System.out.println("sum "+sum);
        double avg = Math.round((double)sum/(N-div*2));

        System.out.println((int)avg);
    }
}
