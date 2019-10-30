public class MaxMulti {

    public int maxProduct1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = nums[0];

        for (int i = 0; i < nums.length; i ++) {
            int pre = nums[i];
            if (pre > max) {
                max = pre;
            }
            for (int j = i + 1; j < nums.length; j ++) {
                int cur = pre * nums[j];
                if (cur > max) {
                    max = cur;
                }

                pre = cur;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        MaxMulti maxMulti = new MaxMulti();
        System.out.println(maxMulti.maxProduct1(new int[]{0, 2}));
    }
}
