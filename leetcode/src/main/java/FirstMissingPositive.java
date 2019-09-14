public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if (nums == null) {
            return 1;
        }

        int len = nums.length;
        if (len == 0) {
            return 1;
        }

        int[] tmp = new int[len + 1];

        for (int i = 0; i < len; i ++) {
            if (nums[i] <= 0) {
                continue;
            }

            if (nums[i] > len) {
                continue;
            }

            tmp[nums[i]] = 1;
        }


        for (int i = 1; i < tmp.length; i ++) {
            if (tmp[i] == 0) {
                return i;
            }
        }

        return tmp.length;


    }

    public static void main(String[] args) {
        int[] array = new int[] {1};

        System.out.println(new FirstMissingPositive().firstMissingPositive(array));
    }
}
