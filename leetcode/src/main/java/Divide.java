public class Divide {

    public int divide(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean fpositive = dividend >= 0;
        boolean spositive = divisor > 0;
        boolean positive = (fpositive && spositive) || (!fpositive && !spositive);

        long divd = Math.abs(dividend);
        long divi = Math.abs(divisor);

        if (dividend == Integer.MIN_VALUE) {
            divd = Integer.MAX_VALUE + 1L;
        }
        if (divisor == Integer.MIN_VALUE) {
            divi = Integer.MAX_VALUE + 1L;
        }

        int i = 0;
        while (divd >= divi) {
            i++;
            divd -= divi;
        }

        return positive? i : (0 - i);


    }

    public int divide2(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean fpositive = dividend >= 0;
        boolean spositive = divisor > 0;
        boolean positive = (fpositive && spositive) || (!fpositive && !spositive);

        long divd = Math.abs(dividend);
        long divi = Math.abs(divisor);

        if (dividend == Integer.MIN_VALUE) {
            divd = Integer.MAX_VALUE + 1L;
        }
        if (divisor == Integer.MIN_VALUE) {
            divi = Integer.MAX_VALUE + 1L;
        }

        long base = divi;
        int sum = 0;
        while (divd >= base) {
            int i = 1;
            divi = base;
            while (divd >= (divi = divi << 1)) {
                i = i << 1;
            }
            sum += i;
            divd = divd - (divi >> 1);
        }



        return positive? sum : (0 -sum);
    }


    public static void main(String[] args) {
        Divide divide = new Divide();
        System.out.println(divide.divide2(1,
                1));
        System.out.println();
    }

}
