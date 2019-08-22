import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class MaxLengthSubString {

    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        int length = s.length();
        if (length == 0) {
            return 0;
        }

        Map<Character,Integer> charIndexMap = new HashMap<>();
        int max = 1;
        int cur = 0;
        int header = 0;
        for (int i = 0; i < length; i ++) {
            char curChar = s.charAt(i);
            Integer hasIndex = charIndexMap.get(curChar);
            if (hasIndex != null && hasIndex >= header) {
                if (cur > max) {
                    max = cur;
                }
                header = hasIndex + 1;
                cur = i - hasIndex;
                charIndexMap.put(curChar, i);
            }
            else {
                charIndexMap.put(curChar, i);
                cur++;
            }
        }

        if (cur > max) {
            max = cur;
        }

        return max;
    }


    public int slidWindow(String s) {
        if (s == null) {
            return 0;
        }

        int length = s.length();
        if (length == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;
        int max = 0;

        Map<Character, Integer> map = new HashMap<>();
        while (i < length && j < length) {
            Integer curIndex = map.get(s.charAt(j));
            if (curIndex != null) {
                i = Math.max(i, curIndex + 1) ;
            }

            max = Math.max(max, j - i + 1);
            map.put(s.charAt(j), j);
            j++;
        }

        return max;
    }

    public static void main(String[] args) {
        MaxLengthSubString maxLengthSubString = new MaxLengthSubString();
        String str = "abba";
        System.out.println(maxLengthSubString.slidWindow(str));
    }
}
