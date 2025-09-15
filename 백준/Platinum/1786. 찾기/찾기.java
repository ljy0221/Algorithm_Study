import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static String P, T;  // P: 패턴(찾을 문자열), T: 텍스트(전체 문자열)
    static int[] failFunc;  // 실패함수 배열 (부분 일치 테이블)
    
    public static void main(String[] args) throws IOException {
        // 1. 입력 받기
        T = br.readLine();  // 전체 텍스트
        P = br.readLine();  // 찾을 패턴

        int textLen = T.length();    // 텍스트 길이
        int targetLen = P.length();  // 패턴 길이

        // 2. 실패함수 (Failure Function) 구성 시작
        failFunc = new int[targetLen];  // 실패함수 배열 초기화
        int len = 0;  // 현재까지 일치하는 접두사-접미사의 길이
        int i = 1;    // 패턴에서 현재 비교하고 있는 위치

        failFunc[0] = 0;  // 첫 번째 원소는 항상 0

        // 실패함수 구성 과정
        while (i < targetLen) {
            // 현재 문자가 접두사의 다음 문자와 일치하는 경우
            if (P.charAt(i) == P.charAt(len)) {
                len++;              // 일치하는 길이 증가
                failFunc[i] = len;  // 현재 위치의 실패함수 값 설정
                i++;                // 다음 위치로 이동
            } else {
                // 일치하지 않는 경우
                if (len != 0) {
                    // 이전 실패함수 값으로 돌아가서 다시 비교
                    len = failFunc[len - 1];
                } else {
                    // 더 이상 돌아갈 곳이 없으면 0으로 설정
                    failFunc[i] = 0;
                    i++;
                }
            }
        }

        // 3. KMP 알고리즘을 사용한 패턴 매칭 시작
        List<Integer> results = new ArrayList<>();  // 매칭된 위치들을 저장할 리스트

        int textIdx = 0;    // 텍스트에서 현재 비교하고 있는 위치
        int targetIdx = 0;  // 패턴에서 현재 비교하고 있는 위치

        // 텍스트 전체를 순회하면서 패턴 찾기
        while (textIdx < textLen) {
            // 현재 문자가 일치하는 경우
            if (T.charAt(textIdx) == P.charAt(targetIdx)) {
                textIdx++;    // 텍스트 인덱스 증가
                targetIdx++;  // 패턴 인덱스 증가
            }

            // 패턴을 완전히 찾은 경우
            if (targetIdx == targetLen) {
                // 매칭된 시작 위치를 결과에 추가 (1-based index로 변환)
                results.add(textIdx - targetIdx + 1);
                // 실패함수를 사용해서 다음 비교 위치 설정
                targetIdx = failFunc[targetIdx - 1];
            } 
            // 문자가 일치하지 않는 경우
            else if (textIdx < textLen && T.charAt(textIdx) != P.charAt(targetIdx)) {
                if (targetIdx != 0) {
                    // 실패함수를 사용해서 패턴 인덱스 조정
                    targetIdx = failFunc[targetIdx - 1];
                } else {
                    // 패턴의 첫 문자부터 일치하지 않으면 텍스트 인덱스만 증가
                    textIdx++;
                }
            }
        }

        // 4. 결과 출력
        sb.append(results.size()).append('\n');  // 매칭된 개수 출력
        for (Integer result : results) {
            sb.append(result).append(' ');  // 매칭된 위치들 출력
        }

        System.out.print(sb);
    }
}