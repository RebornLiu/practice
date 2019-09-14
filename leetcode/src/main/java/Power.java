public class Power {
    public double myPow(double x, int n) {
        double all = 1;
        boolean pos = n < 0;


        if (n == Integer.MIN_VALUE) {
            all = x;
            n = Integer.MAX_VALUE;
        }
        else if (n < 0) {
            n = 0 - n;
        }
        else if (n == 0) {
            return 1;
        }

        while (n != 0) {
            long i = 1;
            double res = x;
            while (i <= n) {
                i = i << 1;
                if (i > n) {
                    break;
                } else {
                    res = res * res;
                }
            }
            all = all * res;
            n = n - (int)(i >> 1);
        }

        if (pos) {
            return 1/ all;
        }
        else {
            return all;
        }
    }




    public static void main(String[] args) {
        System.out.println(new Power().myPow(0.00001, 2147483647));
    }
}
