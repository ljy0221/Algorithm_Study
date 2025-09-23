import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] arr;
    static ArrayList<Integer> lis = new ArrayList<>();
    static int[] parent;
    static int[] lisIndex;
    static int[] lastIndex; // 각 길이별로 마지막으로 추가된 원소의 인덱스

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        parent = new int[n];
        lisIndex = new int[n];
        lastIndex = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(parent, -1);
        lis.add(arr[0]);
        lisIndex[0] = 0;
        lastIndex[0] = 0;

        for (int i = 1; i < n; i++) {
            if(arr[i] > lis.get(lis.size()-1)) {
                parent[i] = lastIndex[lis.size()-1];
                lisIndex[i] = lis.size();
                lastIndex[lis.size()] = i;
                lis.add(arr[i]);
            } else {
                int pos = Collections.binarySearch(lis, arr[i]);
                if(pos < 0) {
                    pos = -(pos+1);
                }
                lis.set(pos, arr[i]);
                lisIndex[i] = pos;
                lastIndex[pos] = i;
                
                if(pos > 0) {
                    parent[i] = lastIndex[pos-1];
                }
            }
        }

        // LIS 길이 출력
        System.out.println(lis.size());

        // 실제 LIS 복원
        ArrayList<Integer> result = new ArrayList<>();
        int current = lastIndex[lis.size()-1];
        while(current != -1) {
            result.add(arr[current]);
            current = parent[current];
        }

        Collections.reverse(result);
        for(int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if(i < result.size()-1) sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}