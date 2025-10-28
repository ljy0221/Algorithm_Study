import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int maxLength = 0;
        
        HashMap<Integer, Integer> fruitCounts = new HashMap<>();

        for (int right = 0; right < N; right++) {
            int currentFruit = S[right];

            fruitCounts.put(currentFruit, fruitCounts.getOrDefault(currentFruit, 0) + 1);

            while (fruitCounts.size() > 2) {
                int leftFruit = S[left];
                
                int count = fruitCounts.get(leftFruit);
                if (count - 1 == 0) {
                    fruitCounts.remove(leftFruit);
                } else {
                    fruitCounts.put(leftFruit, count - 1);
                }
                
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}