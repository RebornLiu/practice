import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        Set<String> pre = new HashSet<>();
        pre.add("()");

        Set<String> cur = new HashSet<>();

        for (int i = 1; i < n; i ++) {

            for (String p : pre) {
                int pLen = p.length();
                for (int j = 0; j < pLen; j ++) {
                    cur.add(p.substring(0,j + 1) + "()" + p.substring(j + 1, pLen));
                }
            }

            Set<String> tmp = pre;
            pre = cur;
            cur = tmp;
            cur.clear();
        }

        return new ArrayList<>(pre);
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> list = generateParenthesis.generateParenthesis(3);
        System.out.println(JSON.toJSONString(list));
    }

}
