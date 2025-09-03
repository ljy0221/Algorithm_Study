import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] memo = new int[11];

        memo[0]=memo[1]=1;
        memo[2]=2;
        for (int i = 3; i <= 10; i++) {
            memo[i]=memo[i-1]+memo[i-2]+memo[i-3];
        }

        while (T-->0) {
            int N = Integer.parseInt(br.readLine());

            System.out.println(memo[N]);
        }
    }
}
