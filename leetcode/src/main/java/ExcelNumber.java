public class ExcelNumber {

    public int titleToNumber(String s) {

        int len = s.length();
        int rs = (s.charAt(0) - 'A' + 1);
        for (int i = 1; i < len; i ++) {
            rs = rs * 26 + (s.charAt(i) - 'A' + 1);
        }

        return rs;
    }
}
