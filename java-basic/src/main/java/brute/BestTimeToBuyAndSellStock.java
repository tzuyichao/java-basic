package brute;

/**
 * = 121
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if(prices.length == 1) {
            return 0;
        }
        int buyIdx = 0;
        int sellIndex = 0;
        int previousBestProfit = 0;
        for(int i=1; i<prices.length; i++) {
            int diff = prices[i] - prices[buyIdx];
            if(diff > previousBestProfit) {
                sellIndex = i;
                previousBestProfit = diff;
            } else if(diff < 0) {
                buyIdx = i;
            }
        }
        return previousBestProfit;
    }
}
