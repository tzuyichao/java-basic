package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * = 122
 */
public class BestTimeToBuyAndSellStock2 {
    static class Transaction {
        int buyIndex;
        int sellIndex;
        int profit;
    }

    public int maxProfit(int[] prices) {
        if(prices.length == 1) {
            return 0;
        }
        List<Transaction> transactions = new ArrayList<>();
        int buyIdx = 0;
        for(int i=1; i<prices.length; i++) {
            int diff = prices[i] - prices[buyIdx];

        }

        if(transactions.isEmpty()) {
            return 0;
        } else {
            return transactions.stream()
                    .map(transaction -> transaction.profit)
                    .reduce((total, profit) -> total + profit)
                    .get();
        }
    }
}
