public class FindTopK {
    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length) {
            return -1;
        }
        return quickHelper(nums, 0, nums.length - 1, k);
    }

    private int quickHelper(int[] nums, int left, int right, int k) {
        int sorted = nums[left];
        int i= left;
        int j = right;
        while (i < j) {
            while (i < j && nums[j] >= sorted) {
                j --;
            }

            if (i < j) {
                nums[i] = nums[j];
                i ++;
            }

            while (i < j && nums[i] < sorted ) {
                i ++;
            }

            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }

        if (right - i + 1 == k) {
            return sorted;
        }

        if (right - i + 1 < k) {
            return quickHelper(nums, left, i - 1, k - (right - i + 1));
        }
        else {
            return quickHelper(nums, i + 1, right, k);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};

        System.out.println(new FindTopK().findKthLargest(nums, 4));
    }
}
