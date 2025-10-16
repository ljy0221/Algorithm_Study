import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            m = Integer.parseInt(br.readLine());
            List<Friend> friends = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                friends.add(new Friend(name, num));
            }

            Collections.sort(friends);

            for (int i = 0; i < friends.size(); i++) {
                sb.append(friends.get(i).name);
                if(i < friends.size()-1) {
                    sb.append(", ");
                }
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

    private static class Friend implements Comparable<Friend> {
        String name;
        int close;
        public Friend(String name, int close) {
            this.name = name;
            this.close = close;
        }
        
        @Override
        public int compareTo(Friend o) {
            return Integer.compare(o.close, this.close);
        }
    }
}
