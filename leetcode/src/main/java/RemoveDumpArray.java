import com.alibaba.fastjson.JSON;

public class RemoveDumpArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }

        int header = 0;
        int tail = 1;

        while (tail < nums.length) {
            if (nums[header] != nums[tail]) {
                nums[header + 1] = nums[tail];
                header++;
            }
            tail++;
        }

        return header + 1;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1,1,2};

        RemoveDumpArray removeDumpArray = new RemoveDumpArray();
        int i  = removeDumpArray.removeDuplicates(array);
        System.out.println(i);
        System.out.println(JSON.toJSONString(array));
    }
}
