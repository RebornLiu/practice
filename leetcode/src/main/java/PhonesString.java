import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PhonesString {

    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        Map<Character, String> keyMap = new HashMap<>();

        keyMap.put('2', "abc");
        keyMap.put('3', "def");
        keyMap.put('4', "ghi");
        keyMap.put('5', "jkl");
        keyMap.put('6', "mno");
        keyMap.put('7', "pqrs");
        keyMap.put('8', "tuv");
        keyMap.put('9', "wxyz");

        int len = digits.length();
        List<String> strs = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            strs.add(keyMap.get(digits.charAt(i)));
        }


        return recure(strs);
    }


    private List<String> recure(List<String> strs) {
        String cur = strs.get(0);
        List<String> list = new ArrayList<>();
        if (strs.size() == 1) {
            for (int i = 0; i < cur.length(); i ++) {
                list.add(cur.substring(i, i + 1));
            }
            return list;
        }

        for (int i = 0; i < cur.length(); i ++) {
            List<String> sub = recure(strs.subList(1, strs.size()));
            for (String s : sub) {
                list.add(cur.substring(i, i + 1) + s);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        PhonesString phonesString = new PhonesString();
        List<String> rs = phonesString.letterCombinations("23");
    }
}
