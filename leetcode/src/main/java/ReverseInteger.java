public class ReverseInteger {


    /**
     * << 移位符号的优先级很低 所以 2 sup 31 - 1(最大整数) 是 (1<<31)/++ - 1
     * Math.abs(Integer.MIN_VALUE) 是错误的 因为溢出 结果还是Integer.Min_value
     *
     * 整数 % / 操作 正数和负数是相似的 只是保留了符号
     * */
    public int reverse(int x) {

        if (x == 0) {
            return 0;
        }

        if (x == Integer.MIN_VALUE) {
            return 0;
        }

        int[] index = new int[10];

        long curx = Math.abs(x);
        int length = 0;
        for (int i = 0; curx != 0; i++) {
            index[i] = (int)(curx % 10);
            curx = curx / 10;
            length ++;
        }

        long rs = index[length - 1];
        long power = 10;
        for (int i = length - 2 ; i >= 0; i --) {
            rs = rs + index[i] * power;
            power = power * 10;
        }

        if(rs > Integer.MAX_VALUE) {
            return 0;
        }
        return (int)(x > 0 ? rs : (0 - rs));
    }

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        int rs = reverseInteger.reverseS(-21648);
        System.out.println(rs);
    }


    public int reverseS(int x) {
        long y = 0;
        //从高位向地位累加
        while (x != 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        if (y > Integer.MAX_VALUE || y < Integer.MIN_VALUE)
            return 0;
        return (int) y;
    }
}
