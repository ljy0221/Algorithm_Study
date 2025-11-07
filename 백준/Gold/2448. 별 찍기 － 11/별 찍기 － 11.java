import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    // 결과를 저장할 2차원 배열. N행 (2N-1)열로 정의할 수도 있지만,
    // 문제의 일반적인 구현을 위해 N x N 정사각형 영역으로 생각하고 구현하는 것이 간편합니다.
    static char[][] arr; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // N x (2N-1) 크기의 출력 영역에서, 별이 찍히는 영역은 N x N 정사각형 안에 포함됩니다.
        // 여기서는 편의상 N x N으로 배열을 선언합니다.
        arr = new char[N][N * 2 - 1]; 
        
        // 배열을 공백으로 초기화합니다.
        for (int i = 0; i < N; i++) {
            Arrays.fill(arr[i], ' ');
        }
        
        // 재귀 함수 호출: 시작 (0, N-1)은 가장 위쪽 꼭짓점의 좌표입니다.
        // 시에르핀스키 삼각형은 (N, 2N-1) 크기 영역에 대칭적으로 그려지므로,
        // (0, N-1)이 중앙 꼭짓점이 됩니다.
        star(0, N - 1, N);
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            // 각 행의 문자열을 생성하여 StringBuilder에 추가합니다.
            // 문자열의 마지막에 불필요한 공백이 포함될 수 있으나,
            // 문제의 출력 형식에 맞추기 위해 arr[i].length()까지 출력해도 무방합니다.
            sb.append(arr[i]).append('\n');
        }
        
        System.out.print(sb);
    }

    /**
     * 시에르핀스키 삼각형 패턴을 재귀적으로 그리는 함수
     * @param r 현재 패턴의 가장 위쪽 꼭짓점의 행 인덱스
     * @param c 현재 패턴의 가장 위쪽 꼭짓점의 열 인덱스
     * @param size 현재 패턴의 높이 (N/2, N/4, ...)
     */
    public static void star(int r, int c, int size) {
        // 1. 종료 조건 (가장 작은 패턴, size=3)
        if (size == 3) {
            // N=3의 기본 패턴을 그립니다.
            // r: 0행, c: 중앙 (N-1)
            
            // 첫 번째 줄: *
            arr[r][c] = '*';
            
            // 두 번째 줄: * *
            arr[r + 1][c - 1] = '*';
            arr[r + 1][c + 1] = '*';
            
            // 세 번째 줄: *****
            for (int i = 0; i < 5; i++) {
                arr[r + 2][c - 2 + i] = '*';
            }
            return;
        }

        // 2. 재귀 호출 (size > 3)
        int newSize = size / 2;
        
        // 1) 상단 중앙 삼각형
        star(r, c, newSize);
        
        // 2) 하단 왼쪽 삼각형
        star(r + newSize, c - newSize, newSize);
        
        // 3) 하단 오른쪽 삼각형
        star(r + newSize, c + newSize, newSize);
    }
}