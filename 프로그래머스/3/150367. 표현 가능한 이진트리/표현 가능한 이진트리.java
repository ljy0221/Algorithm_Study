class Solution {
    static public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String bi = Long.toBinaryString(numbers[i]);

            // 포화 이진트리의 리프노드 개수를 만족하는지 확인
            int treeSize = 1;

            while (treeSize < bi.length()) {
                treeSize = treeSize*2+1;
            }
            // 만족하지 않는다면 될때까지 앞에 0을 채움
            while (bi.length() < treeSize) {
                bi = "0" + bi;
            }
            // 홀수 인덱스에 0이 있는지 확인
            if(isValid(bi, 0, bi.length()-1)) {
                // 없으면 답에 1추가
                answer[i] = 1;
            } else {
                // 하나라도 있으면 0추가 후 끝
                answer[i] = 0;
            }

        }

        return answer;
    }



    private static boolean isValid(String bi, int left, int right) {
        if(left > right) return true;
        int mid = (left+right)/2;
        char root = bi.charAt(mid);

        if(root == '0') {
            for (int i = left; i <= right; i++) {
                if(bi.charAt(i) == '1') {
                    return false;
                }
            }
        }

        return isValid(bi, left, mid-1) && isValid(bi, mid+1, right);
    }
}