import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            String operation = br.readLine(); // 명령어 문자열
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            
            // 1. Deque 초기화 및 데이터 파싱
            Deque<Integer> deque = new ArrayDeque<>();
            if (n > 0) {
                // "[1,2,3]" -> "1,2,3" -> ["1", "2", "3"]
                String[] st = arr.substring(1, arr.length() - 1).split(",");
                for (String num : st) {
                    deque.addLast(Integer.valueOf(num));
                }
            }

            // 2. 명령어 처리
            boolean isReversed = false; // 현재 덱의 방향: false=정방향, true=역방향
            boolean isError = false;

            for (char op : operation.toCharArray()) {
                if (op == 'R') {
                    // 뒤집기: O(1)로 방향만 토글
                    isReversed = !isReversed;
                } else if (op == 'D') {
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    }
                    
                    // 삭제: 방향에 따라 O(1)로 앞 또는 뒤에서 제거
                    if (isReversed) {
                        deque.pollLast(); // 역방향이면 뒤에서 제거
                    } else {
                        deque.pollFirst(); // 정방향이면 앞에서 제거
                    }
                }
            }

            // 3. 결과 출력
            if (isError) {
                sb.append("error").append('\n');
            } else {
                sb.append('[');
                
                // 덱의 요소들을 방향에 맞게 StringBuilder에 추가
                if (isReversed) {
                    // 역방향이면 pollLast()를 사용해 뒤에서부터 원소를 꺼내옴
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollLast());
                        if (!deque.isEmpty()) {
                            sb.append(',');
                        }
                    }
                } else {
                    // 정방향이면 pollFirst()를 사용해 앞에서부터 원소를 꺼내옴
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollFirst());
                        if (!deque.isEmpty()) {
                            sb.append(',');
                        }
                    }
                }
                
                sb.append(']').append('\n');
            }
        }

        System.out.print(sb);
    }
}