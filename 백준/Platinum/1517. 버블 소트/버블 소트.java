import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        
        int[] arr = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Integer[] sorted = new Integer[size];
        for (int i = 0; i < size; i++) {
            sorted[i] = arr[i];
        }
        Arrays.sort(sorted, (a, b) -> Integer.compare(a, b));
        
        Map<Integer, Integer> compress = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (!compress.containsKey(sorted[i])) {
                compress.put(sorted[i], idx++);
            }
        }
        
        n = idx;
        tree = new int[2 * n];
        
        long swapCount = 0;
        
        for (int i = 0; i < size; i++) {
            int compressedValue = compress.get(arr[i]);
            
            swapCount += query(compressedValue + 1, n);
            
            update(compressedValue, 1);
        }
        
        System.out.println(swapCount);
    }
    
    static void update(int pos, int value) {
        for (tree[pos += n] += value; pos > 1; pos >>= 1) {
            tree[pos >> 1] = tree[pos] + tree[pos ^ 1];
        }
    }
    
    static int query(int l, int r) {
        int res = 0;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res += tree[l++];
            if ((r & 1) == 1) res += tree[--r];
        }
        return res;
    }
}