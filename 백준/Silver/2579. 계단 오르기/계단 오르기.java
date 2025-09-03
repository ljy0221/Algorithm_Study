import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[301];
        int[] result = new int[301];
        
        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }
        
        result[0] = 0;
        if (N >= 1) result[1] = stairs[1];
        if (N >= 2) result[2] = stairs[1] + stairs[2];
        
        for (int i = 3; i <= N; i++) {
            result[i] = stairs[i] + Math.max(result[i-2], stairs[i-1] + result[i-3]);
        }
        
        System.out.println(result[N]);
    }
}