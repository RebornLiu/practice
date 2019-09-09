public class DIyDiv {


    public int divide(int dividend, int divisor) {
        boolean flag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        long up = Math.abs((long)dividend);
        long bottom = Math.abs((long)divisor);
        if (up < bottom) {
            return 0;
        }

        long base = bottom;
        long sum = 0;
        long i = 1;
        while (up >= base) {
            bottom = bottom << 1;
            if (up >= bottom) {
               i = i << 1;
            }
            else {
                sum += i;
                up = up - (bottom >> 1);
                bottom = base;
                i = 1;
            }

        }

        if (flag && sum == (Integer.MAX_VALUE + 1L)) {
            return Integer.MAX_VALUE;
        }

        return flag ? (int)sum : (int)(0 - sum);


    }

    public static void main(String[] args) {
        System.out.println(new DIyDiv().divide(10, 3));
    }
}
