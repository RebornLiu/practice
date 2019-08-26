import java.util.HashMap;
import java.util.Map;

public class RomaToInt {

    public int romanToInt(String s) {
        if(s == null) {
            return 0;
        }

        int len = s.length();
        if (len == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>(16);
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        Map<String, Integer> map2 = new HashMap<>(8);
        map2.put("IV", 4);
        map2.put("IX", 9);
        map2.put("XL", 40);
        map2.put("XC", 90);
        map2.put("CD", 400);
        map2.put("CM", 900);


        int res = 0;
        for (int i = 0; i < len;) {
            if (i + 1 == len) {
                res = res + map.get(s.charAt(i));
                return res;
            }

            if (map2.containsKey(s.substring(i, i + 2))) {
                res = res + map2.get(s.substring(i, i + 2));
                i++;
            }
            else {
                res = res + map.get(s.charAt(i));
            }

            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        RomaToInt r = new RomaToInt();
        int rs = r.romanToInt("IICMIV");
        System.out.println(rs);
    }
}
