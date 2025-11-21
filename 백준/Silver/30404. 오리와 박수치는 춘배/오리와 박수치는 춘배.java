import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int cnt = 0;

        /*
        현위치+K범위 안에 다음 울음소리가 있으면
        -> 박수를 다음 위치에서 친다, 다음 다음 위치로 옮긴다
        현위치+K범위 안에 다음 울음소리가 없으면
        -> 박수를 현재 위치에서 친다. 다음 위치로 옮긴다.
        */

        while (idx < N) {
            cnt++;
            
            int end_time = arr[idx] + K;

            while (idx < N && arr[idx] <= end_time) {
                idx++;
            }
        }

        System.out.println(cnt);
    }
}