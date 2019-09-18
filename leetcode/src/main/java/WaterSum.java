public class WaterSum {

    public int trap(int[] height) {
        if (height == null) {
            return 0;
        }

        //过滤掉开头的0
        int i = 0;
        while (height[i] == 0) {
            i ++;
        }

        int left = i;
        int right = i;
        int sum = 0;
        int mid = 0;
        int subS = 0;
        while (right < height.length) {
            while (right < height.length - 1 && height[right] > height[right + 1]) {
                    right++;
                    mid += height[right + 1];
                    subS += (height[right + 1] - height[right]);
            }

            if (right == height.length -1) {
                return sum + subS;
            }

            while (right < height.length - 1 && height[right] < height[right + 1]) {
                    right ++;
                    mid += height[right + 1];
            }
            sum = sum + (right - left) * Math.min(right, left) - mid;

            left = right;
            mid = 0;
        }

        return sum;
    }
}
