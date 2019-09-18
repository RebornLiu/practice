
public class Mysqrt {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        int len = Integer.toString(x).length();//数值长度
        int left = (int)Math.max(1, Math.pow(10, (len >> 1) -1));
        int right = (int)Math.pow(10, Math.max(1, (len >> 1) + 1));
        long mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            long rs = mid * mid;
            if (mid == left) {
                return (int)mid;
            }
            if (rs > x) {

                right = (int)mid;
            } else if (rs == x) {
                return (int)mid;
            } else {
                left = (int)mid;
            }
        }

        return left;
    }


    public int sqrt(int n) {
        if (n == 0) {
            return 0;
        }

        double x = 1;
        double y;
        do {
            y = (x + n/x) / 2;
            if (Math.abs(y - x) < 0.000000001) {
                break;
            }
            x = y;
        } while (true);

        return (int)x;
    }

    public static void main(String[] args) {
        System.out.println(new Mysqrt().sqrt(4));
    }

}
