import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static Person[] persons;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        persons = new Person[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            persons[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] rank = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j) continue;

                if(persons[i].weight > persons[j].weight && persons[i].height > persons[j].height) {
                    rank[j]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(rank[i]+1);

            if(i < N-1) {
                sb.append(" ");
            } 
        }
        
        System.out.println(sb);
    }

    private static class Person {
        int height, weight;

        public Person(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }
    }
}
