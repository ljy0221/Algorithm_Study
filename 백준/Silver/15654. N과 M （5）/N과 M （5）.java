import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        compute(arr, M, new ArrayList<Integer>(), new boolean[N]);
        

        System.out.print(sb);
    }

    private static void compute(int[] arr, int length, ArrayList<Integer> list, boolean[] used) {
        if(list.size() == length) {
            sb.append(print(list)).append('\n');
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> curList = new ArrayList<>(list);
            if(!used[i]) {
                used[i] = true;
                curList.add(arr[i]);
                compute(arr, length, curList, used);
                used[i] = false;
            }

        }
    }

    private static String print(ArrayList<Integer> list) {
        String result = "";

        for (Integer num : list) {
            result += num + " ";
        }

        return result.trim();
    }
}
