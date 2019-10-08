public class MaxProfile {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }
        int len = prices.length;
        if (len == 1) {
            return 0;
        }

        int[] mins = new int[len];
        int[] maxs = new int[len];
        
        mins[0] = prices[0];
        maxs[len - 1] = prices[len - 1];
        for (int i = 1; i < len; i ++) {
            if (prices[i] < mins[i - 1]) {
                mins[i] = prices[i];
            }
            else {
                mins[i] = mins[i - 1];
            }
        }

        for (int i = len - 2; i >= 0; i --) {
            if (prices[i] > maxs[i + 1]) {
                maxs[i] = prices[i];
            }
            else {
                maxs[i] = maxs[i + 1];
            }
        }

        int maxP = 0;
        for (int i = 0; i < len - 1; i ++) {
            if (maxs[i + 1] - mins[i] > maxP) {
                maxP = maxs[i + 1] - mins[i];
            }    
        }

        return maxP;
    }
}