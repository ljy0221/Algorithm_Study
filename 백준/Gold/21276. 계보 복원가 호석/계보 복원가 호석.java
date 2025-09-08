import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받기
        int n = Integer.parseInt(br.readLine());
        String[] people = br.readLine().split(" ");
        int m = Integer.parseInt(br.readLine());
        
        // 조상-후손 관계 저장
        Map<String, Set<String>> ancestors = new HashMap<>(); // person -> 모든 조상들
        Map<String, Set<String>> descendants = new HashMap<>(); // person -> 모든 후손들
        
        // 초기화
        for (String person : people) {
            ancestors.put(person, new HashSet<>());
            descendants.put(person, new HashSet<>());
        }
        
        // 조상-후손 관계 입력
        for (int i = 0; i < m; i++) {
            String[] relation = br.readLine().split(" ");
            String descendant = relation[0];
            String ancestor = relation[1];
            
            ancestors.get(descendant).add(ancestor);
            descendants.get(ancestor).add(descendant);
        }
        
        // 직계 부모-자식 관계 찾기
        Map<String, Set<String>> children = new HashMap<>();
        for (String person : people) {
            children.put(person, new TreeSet<>());
        }
        
        for (String person : people) {
            // person의 직계 부모 찾기
            Set<String> directParents = new HashSet<>();
            
            for (String ancestor : ancestors.get(person)) {
                boolean isDirect = true;
                
                // ancestor가 person의 다른 조상의 후손인지 확인
                for (String otherAncestor : ancestors.get(person)) {
                    if (!otherAncestor.equals(ancestor) && 
                        descendants.get(otherAncestor).contains(ancestor)) {
                        isDirect = false;
                        break;
                    }
                }
                
                if (isDirect) {
                    directParents.add(ancestor);
                }
            }
            
            // 직계 부모-자식 관계 설정
            for (String parent : directParents) {
                children.get(parent).add(person);
            }
        }
        
        // 시조 찾기 (다른 사람의 후손이 아닌 사람들)
        Set<String> founders = new TreeSet<>();
        for (String person : people) {
            boolean isFounder = true;
            for (String other : people) {
                if (!other.equals(person) && descendants.get(other).contains(person)) {
                    isFounder = false;
                    break;
                }
            }
            if (isFounder) {
                founders.add(person);
            }
        }
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        
        // 가문의 개수
        sb.append(founders.size()).append("\n");
        
        // 시조들의 이름 (사전순)
        for (String founder : founders) {
            sb.append(founder).append(" ");
        }
        sb.append("\n");
        
        // 각 사람의 정보 (사전순)
        String[] sortedPeople = people.clone();
        Arrays.sort(sortedPeople);
        
        for (String person : sortedPeople) {
            sb.append(person).append(" ");
            sb.append(children.get(person).size());
            
            for (String child : children.get(person)) {
                sb.append(" ").append(child);
            }
            sb.append("\n");
        }
        
        System.out.print(sb.toString());
    }
}