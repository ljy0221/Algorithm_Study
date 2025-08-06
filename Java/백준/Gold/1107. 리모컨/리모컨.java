import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] broken = new boolean[10];
    static String N;
    static int M;
    static final String startChannel = "100";

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = br.readLine();
        M = Integer.parseInt(br.readLine());
        
        if(M == 0) {
            System.out.println(Math.min(N.length(), Math.abs(100 - Integer.parseInt(N))));
            return;
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int index = Integer.parseInt(st.nextToken());
            broken[index] = true;
        }
        
        if(N.equals(startChannel)) {
            System.out.println(0);
            return;
        }

        int currentChannel = Integer.parseInt(startChannel);
        
        int onlyUpDown = Math.abs(Integer.parseInt(N) - currentChannel);
        int complexUsage = Integer.MAX_VALUE;

        int targetN = Integer.parseInt(N);
        int digits = N.length();
        int maxRange = (int) Math.pow(10, digits + 1);

        for (int channel = 0; channel < maxRange; channel++) {
            if(canMakeChannel(channel)) {
                int usage = String.valueOf(channel).length() + Math.abs(channel - targetN);
                complexUsage = Math.min(complexUsage, usage);
            }
        }

        System.out.println(Math.min(onlyUpDown, complexUsage));
    }

    static boolean canMakeChannel(int channel) {
        String channelStr = String.valueOf(channel);
        for (char digit : channelStr.toCharArray()) {
            int digitNum = digit - '0';
            if (broken[digitNum]) {  // 이 버튼이 고장났다면
                return false;
            }
        }
        return true;
    }
}
