import java.util.*;

class Solution {
    static class Date {
        int year, month, day;
        
        public Date (String date) {
            String[] tokens = date.split("\\.");
            
            this.year = Integer.parseInt(tokens[0]);
            this.month = Integer.parseInt(tokens[1]);
            this.day = Integer.parseInt(tokens[2]);
        }

        public Date (int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }


        public int getTotalDates() {
            return (this.year-2000)*12*28 + ((this.month-1)*28) + this.day;
        }

        public Date addDate(int month) {
            int nYear = this.year;
            int nMonth = this.month+month;
            int nDay = this.day;

            if(nMonth > 12) {
                nYear++;
                nMonth -= 12;
            }
            
            return new Date(nYear, nMonth, nDay);
        }
    }
    


    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> validate = new HashMap<>();
        StringTokenizer st;
        
        Date todayDate = new Date(today);
        
        for (String str : terms) {
            st = new StringTokenizer(str);

            validate.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < privacies.length; i++) {
            String str = privacies[i];
            st = new StringTokenizer(str);

            Date thisDate = new Date(st.nextToken());
            int term = validate.get(st.nextToken());

            Date endDate = thisDate.addDate(term);

            if(todayDate.getTotalDates() >= endDate.getTotalDates()) {
                answer.add(i+1);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}