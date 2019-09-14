public class MaxSum {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int cur = nums[0];
        for (int i = 1; i < nums.length;i++) {
            if (cur < 0) {
                cur = nums[i];
            }
            else {
                cur += nums[i];
            }

            max = Math.max(max, cur);

        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1};
        System.out.println(new MaxSum().maxSubArray(nums));
    }
}
