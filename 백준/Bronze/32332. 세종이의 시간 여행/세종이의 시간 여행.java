import java.io.*;
import java.util.*;

public class Main {
    
    // 수학적 모듈로 연산을 위한 헬퍼 함수
    private static int mod(int a, int n) {
        return (a % n + n) % n;
    }

    // ⭐ 핵심 수정: 총 일수 계산 공식 (1월 1일 = 1일)
    private static int calculateTotalDay(int Y, int M, int D) {
        // Y*360: Y년 시작 시점 (Y년 1월 1일보다 360일 * (Y-1) 만큼 앞)
        // (M-1)*30: M월 시작 시점 (M-1)개월치 일수
        // D: 현재 월의 일
        return Y * 360 + (M - 1) * 30 + D; 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 

        // 1. 현재 시점 (Y0, M0, D0, T0, P0) 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Y0 = Integer.parseInt(st.nextToken());
        int M0 = Integer.parseInt(st.nextToken());
        int D0 = Integer.parseInt(st.nextToken());
        double T0 = Double.parseDouble(st.nextToken());
        double P0 = Double.parseDouble(st.nextToken());

        // 1-A. 현재 시점의 총 일수 계산
        int totalDay0 = calculateTotalDay(Y0, M0, D0);

        // 2. 목표 시점 (Y1, M1, D1, T1, P1) 입력
        st = new StringTokenizer(br.readLine());
        int Y1 = Integer.parseInt(st.nextToken());
        int M1 = Integer.parseInt(st.nextToken());
        int D1 = Integer.parseInt(st.nextToken());
        double T1 = Double.parseDouble(st.nextToken());
        double P1 = Double.parseDouble(st.nextToken());

        // 2-A. 목표 시점의 총 일수 계산
        int totalDay1 = calculateTotalDay(Y1, M1, D1);

        // 3. 입력해야 할 시공간 좌표 계산
        
        // 3-A. 시간 계산: Input_Day = 2 * Current_Day - Target_Day
        int totalDay_in = 2 * totalDay0 - totalDay1;

        // 3-B. 총 일수를 연/월/일로 역변환
        
        // 1. 0 기반 인덱스 (총 일수 - 1). 
        int day_index = totalDay_in - 1; 
        
        // 2. 연도 계산 (360일 단위)
        // Y_in = day_index / 360;  -> Java의 정수 나눗셈은 음수에서 문제 발생 가능
        
        // Math Modulo를 이용해 음수 day_index도 정확히 처리
        // 360일 주기로 반복되는 남은 일수 (0부터 359까지)
        int D_in_index = mod(day_index, 360); 

        // 연도 계산: (day_index - D_in_index) / 360 = (360의 배수) / 360 
        int Y_in = (day_index - D_in_index) / 360;
        
        // 남은 일수 (1부터 360까지)
        int D_rem = D_in_index + 1; 

        // 월 (1부터 12까지)
        // M-1을 구한 후 +1
        int M_in = D_in_index / 30 + 1; 

        // 일 (1부터 30까지)
        // D-1을 구한 후 +1
        int D_in = mod(D_in_index, 30) + 1; 

        // 3-C. 공간 계산: Input = 2 * Current - Target
        double T_in = 2 * T0 - T1;
        double P_in = 2 * P0 - P1;
        
        // 4. 출력 형식 지정
        sb.append(Y_in).append(" ")
          .append(M_in).append(" ")
          .append(D_in).append(" ");
          
        sb.append(String.format(Locale.US, "%.3f", T_in)).append(" ")
          .append(String.format(Locale.US, "%.3f", P_in)); 

        System.out.println(sb.toString());
    }
}