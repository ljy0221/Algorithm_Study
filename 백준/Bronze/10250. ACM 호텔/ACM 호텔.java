import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int floor, room;
            
            if (N % H == 0) {
                floor = H;          // 최고층
                room = N / H;       // 방 번호
            } else {
                floor = N % H;      // 층수
                room = N / H + 1;   // 방 번호
            }

            String roomNumber = String.valueOf(floor);
            if (room < 10) {
                roomNumber += "0" + String.valueOf(room);
            } else {
                roomNumber += String.valueOf(room);
            }
            System.out.println(roomNumber);
        }
    }
}