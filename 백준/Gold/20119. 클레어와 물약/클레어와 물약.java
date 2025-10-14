import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M, L;
    static List<Integer>[] recipes;
    static List<Integer>[] canMake;
    static int[] needCount;
    static boolean[] have;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        recipes = new ArrayList[M];
        canMake = new ArrayList[N + 1];
        needCount = new int[M];
        have = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            recipes[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            canMake[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            needCount[i] = len;
            
            for (int j = 0; j < len; j++) {
                int ingredient = Integer.parseInt(st.nextToken());
                recipes[i].add(ingredient);
                canMake[ingredient].add(i);
            }
            int result = Integer.parseInt(st.nextToken());
            recipes[i].add(result);
        }

        L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            int potion = Integer.parseInt(st.nextToken());
            have[potion] = true;
            queue.offer(potion);
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int recipeIdx : canMake[current]) {
                needCount[recipeIdx]--;

                if (needCount[recipeIdx] == 0) {
                    int resultPotion = recipes[recipeIdx].get(recipes[recipeIdx].size() - 1);
                    if (!have[resultPotion]) {
                        have[resultPotion] = true;
                        queue.offer(resultPotion);
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (have[i]) {
                result.add(i);
            }
        }

        sb.append(result.size()).append('\n');
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (i != result.size() - 1) {
                sb.append(" ");
            }
        }

        System.out.print(sb);
    }
}