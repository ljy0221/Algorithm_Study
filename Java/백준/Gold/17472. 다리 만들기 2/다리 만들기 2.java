import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 입력: N-세로크기, M-가로크기
	 * 		지도 정보(0-바다, 1-땅)
	 * 
	 * 저장할 데이터:
	 * 		다리 놓기를 시작 가능한 좌표들 -> 큐?
	 * 
	 * 출력: 모든 섬을 연결하는 다리 길이의 최솟값(없으면 -1)
	 */
	
	static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge> {
		int start, end, weight;
		
		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}
	
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int M;
	static int islandNum = 1;
	static int[][] bridges;
	static int[] parent;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
			}
		}
		
		// 영역 구분
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {  // 땅이면서 방문하지 않은 곳만
				    markIsland(i, j);
				    islandNum++;
				}
			}
		}
		
		// 섬별 연결 확인 배열
		bridges = new int[islandNum][islandNum];
		for(int i = 0; i < islandNum; i++) {
		    Arrays.fill(bridges[i], Integer.MAX_VALUE);
		}
		
		int start;
		// 가로 확인
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					start = map[i][j];
					makeBridge(start, i, j, true);
				}
			}
		}
		
		// 세로 확인
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					start = map[i][j];
					makeBridge(start, i, j, false);
				}
			}
		}
		
		int result = prim();
		System.out.println(result);
	}
	
	public static void markIsland(int row, int col) {
		visited[row][col] = true;
		map[row][col] = islandNum;
		
		for(int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			
			if(nr >= 0&& nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == 1) {
				markIsland(nr, nc);
			}
		}
	}
	
	public static void makeBridge(int start, int row, int col, boolean isHorizontal) {
	    if(isHorizontal) {
	    	// 왼쪽 방향 확인
		    if(col - 1 >= 0 && map[row][col-1] == 0) {
		        int len = 0;
		        int c = col - 1;
		        while(c >= 0 && map[row][c] == 0) {
		            len++;
		            c--;
		        }
		        if(c >= 0 && map[row][c] > 0 && map[row][c] != start && len >= 2) {
		            int end = map[row][c];
		            bridges[start][end] = Math.min(bridges[start][end], len);
		            bridges[end][start] = Math.min(bridges[end][start], len);
		        }
		    }
		    
		    // 오른쪽 방향 확인
		    if(col + 1 < M && map[row][col+1] == 0) {  // 수정됨
		        int len = 0;
		        int c = col + 1;
		        while(c < M && map[row][c] == 0) {  // 수정됨
		            len++;
		            c++;
		        }
		        if(c < M && map[row][c] > 0 && map[row][c] != start && len >= 2) {  // 수정됨
		            int end = map[row][c];
		            bridges[start][end] = Math.min(bridges[start][end], len);
		            bridges[end][start] = Math.min(bridges[end][start], len);
		        }
		    }
	    } else {
	    	// 위
		    if(row - 1 >= 0 && map[row-1][col] == 0) {  // 바로 옆이 바다인지 확인
		        int len = 0;
		        int r = row - 1;
		        while(r >= 0 && map[r][col] == 0) {  // 바다인 동안 계속
		            len++;
		            r--;
		        }
		        if(r >= 0 && map[r][col] > 0 && map[r][col] != start && len >= 2) {
		            int end = map[r][col];
		            bridges[start][end] = Math.min(bridges[start][end], len);
		            bridges[end][start] = Math.min(bridges[end][start], len); // 양방향
		        }
		    }
		    
		    // 아래
		    if(row + 1 < N && map[row+1][col] == 0) {  // 바로 옆이 바다인지 확인
		        int len = 0;
		        int r = row + 1;
		        while(r < N && map[r][col] == 0) {  // 바다인 동안 계속
		            len++;
		            r++;
		        }
		        if(r < N && map[r][col] > 0 && map[r][col] != start && len >= 2) {
		            int end = map[r][col];
		            bridges[start][end] = Math.min(bridges[start][end], len);
		            bridges[end][start] = Math.min(bridges[end][start], len); // 양방향
		        }
		    }
	    }
	}
	
	// 초기화
	static void makeSet(int n) {
	    parent = new int[n];
	    for(int i = 0; i < n; i++) {
	        parent[i] = i;  // 자기 자신이 부모
	    }
	}

	// 루트 찾기 (경로 압축)
	static int find(int x) {
	    if(parent[x] != x) {
	        parent[x] = find(parent[x]);
	    }
	    return parent[x];
	}

	// 합치기
	static boolean union(int x, int y) {
	    int rootX = find(x);
	    int rootY = find(y);
	    
	    if(rootX == rootY) return false;  // 이미 같은 집합
	    
	    parent[rootY] = rootX;
	    return true;
	}
	
	public static int kruskal() {
	    // 1. 간선 리스트 생성
	    List<Edge> edges = new ArrayList<>();
	    for(int i = 0; i < islandNum; i++) {
	        for(int j = i + 1; j < islandNum; j++) {
	            if(bridges[i][j] != Integer.MAX_VALUE) {
	                edges.add(new Edge(i, j, bridges[i][j]));
	            }
	        }
	    }
	    
	    // 2. 간선 정렬
	    Collections.sort(edges);
	    
	    // 3. Union-Find 초기화
	    makeSet(islandNum);
	    
	    // 4. 크루스칼 실행
	    int totalCost = 0;
	    int edgeCount = 0;
	    
	    for(Edge edge : edges) {
	        if(union(edge.start, edge.end)) {
	            totalCost += edge.weight;
	            edgeCount++;
	            
	            if(edgeCount == islandNum - 1) break;  // 완성
	        }
	    }
	    
	    // 5. 모든 섬이 연결되었는지 확인
	    return edgeCount == islandNum - 2 ? totalCost : -1;
	}
	public static int prim() {
	    boolean[] inMST = new boolean[islandNum];
	    int[] minCost = new int[islandNum];
	    Arrays.fill(minCost, Integer.MAX_VALUE);
	    
	    // 1번 섬부터 시작
	    minCost[1] = 0;
	    int totalCost = 0;
	    int edgeCount = 0;
	    
	    for(int i = 0; i < islandNum - 1; i++) {  // 0번은 안 씀
	        // 최소 비용 정점 찾기
	        int minVertex = -1;
	        int minWeight = Integer.MAX_VALUE;
	        
	        for(int v = 1; v < islandNum; v++) {
	            if(!inMST[v] && minCost[v] < minWeight) {
	                minWeight = minCost[v];
	                minVertex = v;
	            }
	        }
	        
	        if(minVertex == -1) return -1;  // 연결 불가능
	        
	        // MST에 추가
	        inMST[minVertex] = true;
	        totalCost += minWeight;
	        edgeCount++;
	        
	        // 인접 정점들의 최소 비용 갱신
	        for(int next = 1; next < islandNum; next++) {
	            if(!inMST[next] && bridges[minVertex][next] != Integer.MAX_VALUE) {
	                minCost[next] = Math.min(minCost[next], bridges[minVertex][next]);
	            }
	        }
	    }
	    
	    return edgeCount == islandNum - 1 ? totalCost : -1;  // 실제 섬 개수는 islandNum-1
	}
}
