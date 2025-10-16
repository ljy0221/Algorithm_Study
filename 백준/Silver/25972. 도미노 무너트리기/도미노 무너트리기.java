import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static List<Domino> dominos;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dominos = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int a, l;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            dominos.add(new Domino(a, l));
        }

        Collections.sort(dominos);

        if(N == 0) {
            System.out.println(0);
            return;
        }

        int breaks = 0;
        int idx = 0;

        while (idx < N) {
            breaks++;
            
            Domino currentDomino = dominos.get(idx);
            long maxReachable = (long)currentDomino.pos+currentDomino.len;

            int j = idx+1;

            while (j < N && dominos.get(j).pos <= maxReachable) {
                Domino nextDomino = dominos.get(j);

                currentDomino = nextDomino;
                maxReachable = (long)currentDomino.pos+currentDomino.len;

                j++;
            }
            idx = j;
        }

        System.out.println(breaks);
    }

    private static class Domino implements Comparable<Domino>{
        int pos, len;

        public Domino(int pos, int len) {
            this.pos = pos;
            this.len = len;
        }

        @Override
        public int compareTo(Domino o) {
            return Integer.compare(this.pos, o.pos);
        }
        
    }
}
