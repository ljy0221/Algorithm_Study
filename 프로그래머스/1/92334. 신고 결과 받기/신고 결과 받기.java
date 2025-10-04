import java.util.*;

public class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reporterMap = new HashMap<>();
        Map<String, Integer> reportedCount = new HashMap<>();
        for (String id : id_list) {
            reporterMap.put(id, new HashSet<>());
            reportedCount.put(id, 0);
        }

        StringTokenizer st;

        for (String string : report) {
            st = new StringTokenizer(string);
            String reporter = st.nextToken();
            String reportee = st.nextToken();

            if (!reporterMap.get(reporter).contains(reportee)) {
                reporterMap.get(reporter).add(reportee);
                reportedCount.put(reportee, reportedCount.get(reportee) + 1);
            }
        }

        Set<String> suspend = new HashSet<>();

        for (String id : id_list) {
            if (reportedCount.get(id) >= k) {
                suspend.add(id);
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String userId = id_list[i];
            int mailCnt = 0;

            for (String reportee : reporterMap.get(userId)) {
                if (suspend.contains(reportee)) {
                    mailCnt++;
                }
            }

            answer[i] = mailCnt;
        }

        return answer;
    }

    // public static void main(String[] args) {
    //     System.out.println(Arrays.toString(solution(new String[] { "muzi", "frodo", "apeach", "neo" },
    //             new String[] { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" }, 2))); // [2,1,1,0]
    //     System.out.println(Arrays.toString(solution(new String[] { "con", "ryan" },
    //             new String[] { "ryan con", "ryan con", "ryan con", "ryan con" }, 3)));// [0,0]
    // }
}
