import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> tagMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            for (int j = 0; j < t; j++) {
                String tag = st.nextToken();
                tagMap.put(tag, tagMap.getOrDefault(tag, 0)+1);
            }
        }

        String mostFrequentTag = "";
        int maxCount = -1;
        int duplicatedCount = 0;
        for (Entry<String, Integer> tag : tagMap.entrySet()) {
            String key = tag.getKey();
            Integer value = tag.getValue();
            if(value > maxCount) {
                maxCount = value;
                duplicatedCount = 1;
                mostFrequentTag = key;
            } else if (value == maxCount) {
                duplicatedCount++;
            }
        }

        System.out.println(duplicatedCount > 1 ? -1 : mostFrequentTag);
    }
}
