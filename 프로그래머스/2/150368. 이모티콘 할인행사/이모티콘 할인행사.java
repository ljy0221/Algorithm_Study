import java.io.*;
import java.util.*;

class Solution {
    int maxSubscribe;
    int maxProfit;
    int[][] users;
    int[] emoticons;
    int[] discounts = {10, 20, 30, 40};
    
    private void permutate(int size, List<Integer> current) {
        if(current.size() == size) {
            check(current);
            return;
        }
        
        for (int discount : discounts) {
            current.add(discount);
            permutate(size, current);
            current.remove(current.size()-1);
        }
    }
    
    private void check(List<Integer> discountsRate) {
        int subscribe = 0;
        int profit = 0;

        for (int[] user : users) {
            int userLimit = user[0];
            int userBudget = user[1];
            int totalCost = 0;

            for (int i = 0; i < emoticons.length; i++) {
                int discount = discountsRate.get(i);

                if(discount >= userLimit) {
                    totalCost += emoticons[i]*(100-discount)/100;
                }
            }

            if (totalCost >= userBudget) {
                subscribe++;
            } else {
                profit += totalCost;
            }
        }

        if (subscribe > maxSubscribe || 
            (subscribe == maxSubscribe && profit > maxProfit)) {
            maxSubscribe = subscribe;
            maxProfit = profit;
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        maxSubscribe = 0;
        maxProfit = 0;
        int n = emoticons.length;

        permutate(n, new ArrayList<Integer>());
        
        return new int[] {maxSubscribe, maxProfit};
    }
}