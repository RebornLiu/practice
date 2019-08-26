public class MyAtoi {

    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        int length = str.length();
        if (length == 0) {
            return 0;
        }

        //空处理
        int i = 0;
        do {
            char c = str.charAt(i);
            if (c  != ' ') {
                break;
            }
            i++;
        } while (i < length);

        if (i == length) {
            return 0;
        }

        boolean nag = false;
        if (str.charAt(i) == '-') {
            nag = true;
            i ++;
        }
        else if (str.charAt(i) == '+') {
            i ++;
        }

        long rs = 0;
        for(; i < length; i ++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                break;
            }
            rs = rs * 10 + str.charAt(i) - '0';
            if (rs > Integer.MAX_VALUE) {
                return nag? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }


        if (!nag && rs > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (nag && rs > Integer.MAX_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int)(nag? (0 - rs) : rs);
    }


    public static void main(String[] args) {

        System.out.println(new MyAtoi().myAtoi("9223372036854775808"));
    }
}
