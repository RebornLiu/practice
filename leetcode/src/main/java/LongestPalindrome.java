public class LongestPalindrome {

    /**
     *  注意奇数和偶数两种情况
     * */
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        longestPalindrome.longestPalindrome("bb");
    }


    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        int length = s.length();
        if (length == 0) {
            return  "";
        }

        int maxStart = 0;
        int maxEnd = 0;
        int max = 0;

        for (int i = 1; i < length; i ++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right <= length - 1) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }

                left --;
                right ++;
            }

            if (max < right - left - 1) {
                max = right - left - 1;
                maxStart = left + 1;
                maxEnd = right - 1;
            }

            left = i - 1;
            right = i;

            while (left >= 0 && right <= length - 1) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left --;
                right ++;
            }

            if (max < right - left - 1) {
                max = right - left - 1;
                maxStart = left + 1;
                maxEnd = right - 1;
            }
        }

        System.out.println("max:" + max);
        System.out.println("maxStr:" + s.substring(maxStart, maxEnd + 1));
        return s.substring(maxStart, maxEnd + 1);
    }
}
