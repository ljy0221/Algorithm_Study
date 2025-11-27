import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    long X = Long.parseLong(st.nextToken());
    long Y = Long.parseLong(st.nextToken());

    int fixedPositions = 0; // 위치가 확정된 자리 수
    Set<Integer> fixedNumbers = new HashSet<>(); // 위치 확정된 숫자들
    Set<Integer> unfixedNumbers = new HashSet<>(); // 위치 미확정이지만 포함되어야 하는 숫자들

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      if (a != 0) {
        fixedPositions++;
        fixedNumbers.add(b);
      } else {
        unfixedNumbers.add(b);
      }
    }

    // 남은 자리 수
    int remainingPositions = N - fixedPositions;
    
    // 남은 자리에 반드시 들어가야 하는 숫자 개수
    int mustInclude = unfixedNumbers.size();
    
    // 자유롭게 선택할 수 있는 자리 수
    int freePositions = remainingPositions - mustInclude;
    
    // 전체 사용된 숫자 (확정 + 미확정)
    Set<Integer> allUsed = new HashSet<>();
    allUsed.addAll(fixedNumbers);
    allUsed.addAll(unfixedNumbers);
    
    // 선택 가능한 숫자 개수 (1~9 중 사용하지 않은 숫자)
    int availableNumbers = 9 - allUsed.size();

    // 경우의 수 계산
    long W = 1;
    
    if (freePositions < 0 || freePositions > availableNumbers) {
      W = 0;
    } else {
      // 1. 미확정 숫자들을 남은 자리에 배치: P(remainingPositions, mustInclude)
      for (int i = 0; i < mustInclude; i++) {
        W *= (remainingPositions - i);
      }
      
      // 2. 나머지 자유 자리에 가능한 숫자 배치: P(availableNumbers, freePositions)
      for (int i = 0; i < freePositions; i++) {
        W *= (availableNumbers - i);
      }
    }

    // 총 입력 시간
    long totalInputTime = W * X;
    
    // 대기 시간 계산
    long waitCount = (W > 0) ? (W - 1) / 3 : 0;
    long totalWaitTime = waitCount * Y;

    long totalTime = totalInputTime + totalWaitTime;

    System.out.println(totalTime);
  }
}