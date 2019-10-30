public class FindTop {
    public int findPeakElement(int[] nums) {

        int len = nums.length;
        return helper(nums, 0, len - 1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }

        int mid = (left + right) / 2;
        long leftValue = mid - 1 < left ? Long.MIN_VALUE : nums[mid - 1];
        long rightValue = mid + 1 > right ? Long.MIN_VALUE : nums[mid + 1];

        if (nums[mid] > leftValue && nums[mid] > rightValue) {
            return mid;
        }

        if (nums[mid] > leftValue && nums[mid] < rightValue) {
            return helper(nums, mid + 1, right);
        }

        if (nums[mid] > rightValue && nums[mid] < leftValue) {
            return helper(nums, left, mid - 1);
        }

        if (mid > left) {
            return helper(nums, left, mid - 1);
        }

        if (mid < right) {
            return helper(nums, mid + 1, right);
        }

        return -1;
    }

    public static void main(String[] args) {
        FindTop T = new FindTop();
        System.out.println(T.findPeakElement(new int[]{2, 1, 2}));
    }
}
