public class MajarNumber {

    public int majorityElement(int[] nums) {
        int current = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; ) {
            if (current != nums[i]) {
                count --;
                if (count == 0) {
                    current = nums[i + 1];
                    i = i + 2;
                    count = 1;
                }
                else {
                    i = i + 1;
                }
            }
            else {
                count ++;
                i ++;
            }
        }

        return current;
    }
}
