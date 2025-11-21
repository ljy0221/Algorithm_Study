import java.io.*;
import java.util.*;

public class Main {

    static class Room {
        int L;
        int R;
        long people;

        public Room(int L, int R, long people) {
            this.L = L;
            this.R = R;
            this.people = people;
        }
    }

    static int N, Q; 
    static long[] occupancy; 
    static List<Room> allocatedRooms; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        occupancy = new long[N + 1]; 
        allocatedRooms = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("new")) {
                long X = Long.parseLong(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                
                int L = -1;
                for (int i = 1; i <= N - Y + 1; i++) {
                    boolean isAvailable = true;
                    for (int j = i; j < i + Y; j++) {
                        if (occupancy[j] != 0) {
                            isAvailable = false;
                            i = j; 
                            break;
                        }
                    }
                    
                    if (isAvailable) {
                        L = i;
                        break;
                    }
                }
                
                if (L != -1) {
                    int R = L + Y - 1;
                    Room newRoom = new Room(L, R, X);
                    allocatedRooms.add(newRoom);
                    
                    for (int j = L; j <= R; j++) {
                        occupancy[j] = X;
                    }
                    sb.append(L).append(" ").append(R).append("\n");
                } else {
                    sb.append("REJECTED").append("\n");
                }
            } 
            
            else if (command.equals("in")) {
                int A = Integer.parseInt(st.nextToken());
                long B = Long.parseLong(st.nextToken());
                
                Room room = allocatedRooms.get(A - 1);
                room.people += B;
                
                for (int j = room.L; j <= room.R; j++) {
                    occupancy[j] += B;
                }

            } 
            
            else if (command.equals("out")) {
                int A = Integer.parseInt(st.nextToken());
                long B = Long.parseLong(st.nextToken());
                
                Room room = allocatedRooms.get(A - 1);
                room.people -= B;
                
                for (int j = room.L; j <= room.R; j++) {
                    occupancy[j] -= B;
                }
                
                if (room.people == 0) {

                    for (int j = room.L; j <= room.R; j++) {
                        occupancy[j] = 0;
                    }
                    sb.append("CLEAN ").append(room.L).append(" ").append(room.R).append("\n");
                }
            }
        }
        
        System.out.print(sb.toString());
    }
}