import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreaker {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }

        Map<Integer, List<String>> table = this.startWith(s, wordDict);

       /* for (String word : wordDict) {
            if (s.startsWith(word)) {
                boolean hasBreak = wordBreak(s.substring(word.length()), wordDict);
                if (hasBreak) {
                    return true;
                }
            }
        }

        return false;*/

       return hasBreaker(s, 0, table);
    }


    private boolean hasBreaker(String s, int index, Map<Integer, List<String>> table) {
        if (index >= s.length()) {
            return true;
        }
        List<String> starters = table.get(index);
        if (starters == null || starters.isEmpty()) {
            return false;
        }
        else {
            for (String starter : starters) {
                boolean b = hasBreaker(s, starter.length() + index, table);
                if (b) {
                    return true;
                }
            }

            return false;
        }
    }

    private Map<Integer, List<String>> startWith(String s, List<String> words) {
        int size = s.length();
        Map<Integer, List<String>> table = new HashMap<>();
        for (int i = 0; i < size; i ++) {
            for (String word : words) {
                if (s.substring(i).startsWith(word)) {
                    List<String> starter = table.computeIfAbsent(i, k -> new ArrayList<>());
                    starter.add(word);
                }
            }
        }

        return table;
    }

    public static void main(String[] args) {
        //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
       //         ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        //String s = "leetcode";
        List<String> words = new ArrayList<>();
        words.add("a");
        words.add("aa");

        WordBreaker breaker = new WordBreaker();
        boolean rs = breaker.wordBreak(s, words);
        System.out.println(rs);
    }
}
