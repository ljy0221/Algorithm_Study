import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] menu;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        menu = new int[2*N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) {
            menu[i] = Integer.parseInt(st.nextToken());
        }

        
        Set<Integer> menuSet = new HashSet<>();
        int maxSticker = 0;
        int currentSticker = 0;

        for (int i = 0; i < 2*N; i++) {
            int currentMenu = menu[i];

            if(!menuSet.contains(currentMenu)) {
                menuSet.add(currentMenu);
                currentSticker++;
            } else {
                menuSet.remove(currentMenu);
                currentSticker--;
            }

            if (currentSticker > maxSticker) {
                maxSticker = currentSticker;
            }
        }

        
        System.out.println(maxSticker);
    }
}
