import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int[] notes = new int[8];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 8; i++) {
            notes[i] = Integer.parseInt(st.nextToken());
        }

        boolean isAscend = true, isDescend = true;

        for (int i = 0; i < 7; i++) {
            if(notes[i] > notes[i+1]) {
                isAscend = false;
            } else if(notes[i] < notes[i+1]) {
                isDescend = false;
            }
        }

        if(isAscend) {
            System.out.println("ascending");
        } else if(isDescend) {
            System.out.println("descending");
        } else if(!isAscend && !isDescend) {
            System.out.println("mixed");
        }
    }
}
