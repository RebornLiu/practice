public class MaxArea {

    /**
     * 难点是证明双指针没有错过最优解
     *
     * 用S（i，j）表示面积，i， j是下标 假设height[i] < height[j]
     * 向右移动height[i]相当于放弃了S(i, j - 1), S(i, j - 2)....S(i, i+ 1)
     *
     * 因为height[i]是较小的，向左移动height[j]会减少水槽的宽度，但是不能增大水槽的高度（最高是height[i]）,
     * 所以有S(i, j - 1), S(i, j - 2)....S(i, i+ 1) 都小于S（i， j）
     * 因此使用双指针 移动较小的一个是正确的
     * */
    public int maxArea(int[] height) {
        if (height == null) {
            return 0;
        }

        int length = height.length;
        if (length < 2) {
            return 0;
        }

        int left = 0;
        int right = length - 1;
        int max = 0;

        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));

            if (height[left] < height[right]) {
                left ++;
            }
            else {
                right --;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println();maxArea.maxArea(height);
    }
}
