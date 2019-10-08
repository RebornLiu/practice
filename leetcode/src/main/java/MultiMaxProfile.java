public class MultiMaxProfile {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }

        int len = prices.length;
        if (len <= 1) {
            return 0;
        }

        int min = prices[0];
        int profile = 0;
        for (int i = 1; i < len; i ++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            else if (i == len - 1 || prices[i] > prices[i + 1]) {
                profile += (prices[i] - min);
                if (i != len - 1) {
                    min = prices[i + 1];
                }
            }
        }

        return profile;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3};
        MultiMaxProfile profile = new MultiMaxProfile();
        System.out.println("profile:" + profile.maxProfit(prices));
    }
}