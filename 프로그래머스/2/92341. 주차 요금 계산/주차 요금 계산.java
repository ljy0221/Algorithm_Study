import java.util.*;

public class Solution {
    public int[] solution(int[] fees, String[] records) {
        // TreeMap으로 차량번호 순 자동 정렬
        TreeMap<String, List<Integer>> inOutMap = new TreeMap<>();

        StringTokenizer st;

        // 입출차 기록 파싱
        for (String record : records) {
            st = new StringTokenizer(record);

            String time = st.nextToken();
            String carNumber = st.nextToken();
            String type = st.nextToken();

            inOutMap.putIfAbsent(carNumber, new ArrayList<>());
            inOutMap.get(carNumber).add(parseTime(time));
        }

        int[] answer = new int[inOutMap.size()];

        int idx = 0;
        for (Map.Entry<String, List<Integer>> entry : inOutMap.entrySet()) {
            List<Integer> times = entry.getValue();
            int totalTime = 0;

            // IN/OUT 쌍으로 계산
            for (int i = 0; i < times.size(); i += 2) {
                int inTime = times.get(i);
                int outTime;

                // OUT 기록이 없으면 23:59로 처리
                if (i + 1 < times.size()) {
                    outTime = times.get(i + 1);
                } else {
                    outTime = parseTime("23:59");
                }

                totalTime += (outTime - inTime);
            }

            answer[idx++] = calculateFee(totalTime, fees);
        }

        return answer;
    }

    private static int calculateFee(int totalTime, int[] fees) {
        int basicMin = fees[0]; // 기본 시간
        int basicFee = fees[1]; // 기본 요금
        int unitMin = fees[2]; // 단위 시간
        int unitFee = fees[3]; // 단위 요금

        // 기본 시간 이하면 기본 요금만
        if (totalTime <= basicMin) {
            return basicFee;
        }

        // 초과 시간 계산
        int extraTime = totalTime - basicMin;
        int extraFee = (int) Math.ceil((double) extraTime / unitMin) * unitFee;

        return basicFee + extraFee;
    }

    private static int parseTime(String time) {
        String[] tokens = time.split(":");
        return Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
    }

//     public static void main(String[] args) {
//         System.out.println(Arrays.toString(solution(new int[] { 180, 5000, 10, 600 },
//                 new String[] { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
//                         "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" })));
//         // [14600, 34400, 5000]

//         System.out.println(Arrays.toString(solution(new int[] { 120, 0, 60, 591 },
//                 new String[] { "16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT",
//                         "23:58 3961 IN" })));
//         // [0, 591]

//         System.out.println(Arrays.toString(solution(new int[] { 1, 461, 1, 10 },
//                 new String[] { "00:00 1234 IN" })));
//         // [14841]
//     }
}