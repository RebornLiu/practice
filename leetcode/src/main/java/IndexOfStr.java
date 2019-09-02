public class IndexOfStr {

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.equals("")) {
            return 0;
        }

        if (haystack == null || haystack.length() == 0) {
            return -1;
        }

        int hLen = haystack.length();
        int nLen = needle.length();

        for (int i = 0; i < hLen - nLen; i ++) {
            if (haystack.substring(i, i + nLen).equals(needle)) {
                return i;
            }
        }

        return -1;
    }
}
