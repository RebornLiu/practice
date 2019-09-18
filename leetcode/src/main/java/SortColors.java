import com.alibaba.fastjson.JSON;

public class SortColors {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int p0 = 0;
        int p2 = nums.length - 1;
        int cur = p0;

        while (cur <= p2) {
            if (nums[cur] == 2) {
                swap(nums, cur, p2);
                p2 --;
            }
            else if (nums[cur] == 0) {
                swap(nums, cur, p0);

                cur++;
                p0++;
            }
            else if (nums[cur] == 1){
                cur ++;
            }
        }
    }

    private void swap(int[] nums, int first, int sec) {
        int tmp = nums[first];
        nums[first] = nums[sec];
        nums[sec] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2,0,1};
        new SortColors().sortColors(nums);
        System.out.println(JSON.toJSONString(nums));
    }
}
