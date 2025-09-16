import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        while (true) {
            String in = br.readLine();

            if(in.equals("0")) return;

            System.out.println(isPalindrome(in) ? "yes" : "no");
        }
    }

    private static boolean isPalindrome(String in) {
        int l = 0, r = in.length()-1;

        while (l < r) {
            if(in.charAt(l) == in.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }

        return true;
    }
}
