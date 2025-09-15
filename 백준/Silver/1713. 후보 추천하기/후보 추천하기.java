import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());  // 사진틀의 개수
        int M = Integer.parseInt(br.readLine());  // 전체 학생의 추천 횟수
        
        ArrayList<Student> frames = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < M; i++) {
            int studentId = Integer.parseInt(st.nextToken());
            
            // 이미 사진틀에 있는 학생인지 확인
            boolean found = false;
            for(Student student : frames) {
                if(student.id == studentId) {
                    student.count++;  // 추천 횟수 증가 (규칙 4)
                    found = true;
                    break;
                }
            }
            
            // 새로운 학생인 경우 (규칙 2)
            if(!found) {
                if(frames.size() < N) {
                    // 사진틀에 자리가 있는 경우
                    frames.add(new Student(studentId, 1, i));
                } else {
                    // 사진틀이 꽉 찬 경우 - 가장 우선순위가 낮은 학생 제거 (규칙 3)
                    Collections.sort(frames);
                    frames.remove(0);  // 추천수가 가장 적고 가장 오래된 학생 제거 (규칙 5: 추천수 0으로 초기화됨)
                    frames.add(new Student(studentId, 1, i));
                }
            }
        }
        
        // 최종 결과를 학생 번호 순으로 정렬하여 출력
        Collections.sort(frames, (a, b) -> a.id - b.id);
        
        for(int i = 0; i < frames.size(); i++) {
            System.out.print(frames.get(i).id);
            if(i < frames.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}

class Student implements Comparable<Student> {
    int id;
    int count;
    int time;
    
    public Student(int id, int count, int time) {
        this.id = id;
        this.count = count;
        this.time = time;
    }
    
    @Override
    public int compareTo(Student other) {
        // 1순위: 추천 횟수 (오름차순) - 적을수록 우선 제거
        if (this.count != other.count) {
            return this.count - other.count;
        }
        // 2순위: 게시 시간 (오름차순) - 더 오래된 것이 우선 제거
        return this.time - other.time;
    }
}