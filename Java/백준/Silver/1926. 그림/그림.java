import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int N;
	static int M;
	static List<int[]> pos = new ArrayList<>();
	static boolean[][] visited; //방문배열
	
	static int[] ar = {-1,0,1,0};
	static int[] ac = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/그림1926.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
//		pos.add(new ArrayList<>());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//그림 입력
		
		visited = new boolean[N][M]; // 방문배열 쓰기
		max_size = 0; //최대 그림 크기
		int cnt = 0; //그림 개수
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(i,j);
					cnt++;
				}
//				drawing(i, j);
			}
			
		}//그림 찾기 끝
		
		System.out.println(cnt);
		System.out.println(max_size);
		
		
	}//메인 끝
	static int max_size;
	static void bfs(int r, int c) { //더 간단하게 할 수 있을 거 같지만 ... 전혀 모르겠음 ㄷㄷ
		int size =1;
		Deque<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] next = q.poll();
			for(int i=0; i<4; i++) {
				int nr = next[0] + ar[i];
				int nc = next[1] + ac[i];
				
				if(0<=nr&&nr<N && 0<=nc&&nc<M) {
					if(map[nr][nc]==1 && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.add(new int[] {nr,nc});
						size += 1;
					}
				}//if문 끝
			}//사방 탐색 끝
			
		}//와일문 끝
		
		max_size = Math.max(max_size, size);
	}//bfs 함수 끝
	
	static void drawing(int r, int c) {//dfs 탐색
		
	}//함수 끝

}
