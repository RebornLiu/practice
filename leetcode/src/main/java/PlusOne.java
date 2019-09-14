import com.alibaba.fastjson.JSON;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int over = 1;
        for (int i = digits.length - 1; i >= 0; i --) {
            int tmp = (digits[i] + over)/10;
            digits[i] = (digits[i] + over) % 10;
            over = tmp;
        }


        if (over > 0) {
            int[] res = new int[digits.length + 1];
            res[0] = over;
            for (int i = 1; i <= digits.length; i ++) {
                res[i] = digits[i - 1];
            }
            return res;
        }
        else {
            return digits;
        }
    }

    private void reverseArray(int[] array, int len) {
        for (int i = 0; i < len / 2; i ++) {
            int tmp = array[i];
            array[i] = array[len - i - 1];
            array[len - i - 1] = tmp;
        }
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new PlusOne().plusOne(new int[]{9})));
    }
}
