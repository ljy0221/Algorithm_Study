import java.io.*;
import java.util.*;

public class Main {
    
    // 강의 정보를 담는 클래스
    private static class Lecture implements Comparable<Lecture> {
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.start != o.start) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.end, o.end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Lecture> lectures = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(s, t));
        }

        Collections.sort(lectures);

        PriorityQueue<Integer> classroomEnds = new PriorityQueue<>();

        for (Lecture lecture : lectures) {
            int currentStart = lecture.start;
            int currentEnd = lecture.end;

            if (!classroomEnds.isEmpty() && classroomEnds.peek() <= currentStart) {
                classroomEnds.poll();
                classroomEnds.offer(currentEnd);
            } 
            else {
                classroomEnds.offer(currentEnd);
            }
        }

        System.out.println(classroomEnds.size());
    }
}