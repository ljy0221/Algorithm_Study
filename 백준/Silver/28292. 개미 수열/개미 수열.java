import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());

        if(N <= 2) {
            System.out.println(1);
        } else if (N <= 5) {
            System.out.println(2);
        } else {
            System.out.println(3);
        }
    }
}