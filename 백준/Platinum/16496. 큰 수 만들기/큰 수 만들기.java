import java.io.*;
import java.util.*; 

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static List<String> list;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(st.nextToken());
        }

        Collections.sort(list, (o1, o2) -> (o2+o1).compareTo(o1+o2));

        String result = "";

        if(list.get(0).equals("0")) {
            System.out.println(0);
            return;
        }

        for (String num : list) {
            result += num;
        }

        System.out.println(result);
    }
}
