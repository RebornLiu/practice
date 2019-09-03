public class StartAndEnd {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null) {
            return new int[]{-1, -1};
        }
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        return searchRangeStep(nums, 0, nums.length - 1, target);
    }

    private int[] searchRangeStep(int[] nums, int left, int right, int target) {
        if (left > right) {
            return new int[]{-1, -1};
        }

        int mid = (left + right) / 2;

        if (nums[mid] > target) {
            return searchRangeStep(nums, left, mid - 1, target);
        } else if (nums[mid] < target) {
            return searchRangeStep(nums, mid + 1, right, target);
        } else {
            int curLeft = mid - 1;
            int curRight = mid + 1;
            int resLeft;
            int resRight;
            while (curLeft >= left && nums[curLeft] == target) {
                curLeft --;
            }
            if (curLeft < left) {
                resLeft = left;
            }
            else {
                resLeft = curLeft + 1;
            }

            while (curRight <= right && nums[curRight] == target) {
                curRight ++;
            }
            if (curRight > right) {
                resRight = right;
            }
            else {
                resRight = curRight - 1;
            }


            return new int[]{resLeft, resRight};
        }
    }
}
