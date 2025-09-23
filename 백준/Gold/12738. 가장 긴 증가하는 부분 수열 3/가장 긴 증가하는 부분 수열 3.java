import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> ans = new ArrayList<>();

        ans.add(arr[0]);

        for (int i = 1; i < n; i++) {
            if(arr[i] > ans.get(ans.size()-1)) {
                ans.add(arr[i]);
            } else {
                int low = Collections.binarySearch(ans, arr[i]);

                if(low < 0) {
                    low = -(low+1);
                }

                ans.set(low, arr[i]);
            }
        }

        System.out.println(ans.size());
    }
}
