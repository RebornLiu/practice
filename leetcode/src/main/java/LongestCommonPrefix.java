public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs  == null) {
            return "";
        }
        int count = strs.length;
        if(count == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < count; i++) {
            if (strs[i] == null) {
                return "";
            }
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
            }
        }

        for (int i = 0; i < minLen; i ++) {
            for (int j = 0; j < count - 1; j ++) {
                if (strs[j].charAt(i) != strs[j + 1].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0].substring(0, minLen);
    }

    public static void main(String[] args) {
        LongestCommonPrefix longestPalindrome = new LongestCommonPrefix();
        String[] strs = new String[]{"flower","flow",""};
        String rs = longestPalindrome.longestCommonPrefix(strs);
        System.out.println(rs);
    }
}
