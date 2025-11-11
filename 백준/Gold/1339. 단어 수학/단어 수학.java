import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        String[] nums = new String[N];

        for (int i = 0; i < N; i++) {
            nums[i] = br.readLine();
        }

        int[] alphaNum = new int[26];

        for (String num : nums) {
            int powerOfTen = 1;

            for (int i = num.length()-1; i >= 0; i--) {
                char c = num.charAt(i);
                alphaNum[c - 'A'] += powerOfTen;
                powerOfTen *= 10;
            }
        }

        List<Integer> realVal = new ArrayList<>();
        for (int value : alphaNum) {
            if (value > 0) {
                realVal.add(value);
            }
        }

        Collections.sort(realVal, Comparator.reverseOrder());

        long maxSum = 0;
        int num = 9;

        for (int val : realVal) {
            maxSum += (long)val*num;
            num--;
        }

        System.out.println(maxSum);
    }
}
