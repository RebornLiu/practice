public class BinarySearchRevertList {
    public int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }

        return searchStep(nums, 0, nums.length - 1, target);
    }

    private int searchStep(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        if (nums[mid] < target) {
            if (nums[mid] > nums[right]) {
                return searchStep(nums, mid + 1, right, target);
            }
            else if (nums[mid] > nums[left]){
                return searchStep(nums, mid + 1, right, target);
            }
            else {
                if (target > nums[left]) {
                    return searchStep(nums, left, mid - 1, target);
                }
                else {
                    return searchStep(nums, mid + 1, right, target);
                }
            }
        }
        //nums[mid] > target
        else {
            if (nums[mid] < nums[right]) {
                return searchStep(nums, left, mid - 1, target);
            }
            else if (nums[mid] < nums[left]) {
                return searchStep(nums, left, mid - 1, target);
            }
            else {
                if (target < nums[left]) {
                    return searchStep(nums, mid + 1, right, target);
                }
                else {
                    return searchStep(nums, left, mid - 1, target);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,8,1,2,3};

        BinarySearchRevertList binarySearchRevertList = new BinarySearchRevertList();
        System.out.println(binarySearchRevertList.search(nums, 8));
    }
}
