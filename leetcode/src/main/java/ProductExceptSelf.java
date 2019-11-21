public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] forward = new int[nums.length];
        int[] backward = new int[nums.length];

        forward[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            forward[i] = forward[i - 1] * nums[i];
        }

        backward[nums.length - 1] = nums[nums.length - 1];
        for(int i = nums.length - 2; i >= 0; i --) {
            backward[i] = backward[i + 1] * nums[i];
        }

        int[] res = new int[nums.length];
        res[0] = backward[1];
        res[nums.length - 1] = forward[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i ++) {
            res[i] = forward[i - 1] * backward[i + 1];
        }

        return res;
    }

    public int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];

        res[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            res[i] = res[i - 1] * nums[i];
        }

        int suffix = nums[nums.length - 1];
        res[nums.length - 1] = res[nums.length - 2];
        for (int i = nums.length - 2; i > 0; i --) {
            res[i] = res[ i - 1] * suffix;
            suffix = suffix * nums[i];
        }
        res[0] = suffix;
        return res;
    }
}
