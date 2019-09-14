import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupString {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(this::sortStr))
                .values());
    }


    private String sortStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    public static void main(String[] args) {
        String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(JSON.toJSONString(new GroupString().groupAnagrams(strs)));
    }
}
