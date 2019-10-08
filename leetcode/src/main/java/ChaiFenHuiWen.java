import java.util.*;

import com.alibaba.fastjson.JSON;

public class ChaiFenHuiWen {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        boolean[][] dp = isHuiWen(s);
        List<String> tmp = new ArrayList<>();
        helper(s, res, dp, 0, tmp);

        return res;
    }


    private void helper(String s, List<List<String>> res, boolean[][] dp, int start, List<String> tmp) {
        if (start >= s.length()) {
            //System.out.println(JSON.toJSONString(tmp));
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start + 1; i <= s.length(); i ++) {
            String sub = s.substring(start, i);
            if(dp[start][i - 1]) {
                tmp.add(sub);
                helper(s, res, dp, i, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    /**
     * dp[i][j] = char[i]==char[j] && dp[i + 1][j - 1];
     * @param s
     * @return
     */
    public boolean[][] isHuiWen(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int len = 1; len <= length; len ++) {
            for (int i = 0; i < length - len + 1; i ++) {
                int j = i + len - 1;
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (len < 3 || dp[i + 1][j - 1]);
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        ChaiFenHuiWen chaiFenHuiWen = new ChaiFenHuiWen();
        List<List<String>> res = chaiFenHuiWen.partition("cbbbcc");
        System.out.println(JSON.toJSONString(res));
    }
}