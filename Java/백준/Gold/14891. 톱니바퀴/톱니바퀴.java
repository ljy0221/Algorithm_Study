import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] gears = new int[5][8];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 1; i <= 4; i++) {
            String line = br.readLine();
            for(int j = 0; j < 8; j++) {
                gears[i][j] = line.charAt(j) - '0';
            }
        }
		
		int K = Integer.parseInt(br.readLine());
		
		for(int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken());
			int rotateDir = Integer.parseInt(st.nextToken());
			
			int[] rotations = new int[5];
			rotations[gearNum] = rotateDir;
			
			// left
			for(int i = gearNum; i >= 2; i--) {
				if(gears[i-1][2] != gears[i][6]) {
					rotations[i-1] = -rotations[i];
				} else {
					break;
				}
			}
			
			// right
			for(int i = gearNum; i <= 3; i++) {
				if(gears[i][2] != gears[i+1][6]) {
					rotations[i+1] = -rotations[i];
				} else {
					break;
				}
			}
			
			for(int i = 1; i <= 4; i++) {
				if(rotations[i] != 0) {
					rotate(i, rotations[i]);
				}
			}
		}
		
		int score = 0;
		int[] points = {0, 1, 2, 4, 8};
		
		for(int i = 1; i <= 4; i++) {
            if(gears[i][0] == 1) { // 12시 방향이 S극이면
                score += points[i];
            }
        }
        
        System.out.println(score);
	}
	
	static void rotate(int gearNum, int direction) {
		int[] gear = gears[gearNum];
		
		if(direction == 1) {
			int tmp = gear[7];
			for(int i = 7; i > 0; i--) {
				gear[i] = gear[i-1];
			}
			gear[0] = tmp;
		} else {
			int tmp = gear[0];
			for(int i = 0; i < 7; i++) {
				gear[i] = gear[i+1];
			}
			gear[7] = tmp;
		}
	}
}
