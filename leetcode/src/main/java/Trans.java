import java.util.ArrayList;
import java.util.List;

public class Trans {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<String> nextWords = findNext(beginWord, wordList);
        int step = 2;
        while (!nextWords.isEmpty() && !nextWords.contains(endWord)) {
            List<String> ww = new ArrayList<>();
            for (String next : nextWords) {
                ww.addAll(findNext(next, wordList));
            }
            nextWords = ww;
            step ++;
        }
        if (nextWords.isEmpty()) {
            return 0;
        }
        else {
            return step;
        } 
    }

    private List<String> findNext(String root, List<String> words) {
        List<String> retainWords = new ArrayList<>();
        List<String> nextWords = new ArrayList<>();
        for (String word : words) {
            if (nextString(root, word)) {
                nextWords.add(word);
            }
            else {
                retainWords.add(word);
            }
        }

        words.clear();
        words.addAll(retainWords);
        return nextWords;
    }

    private boolean nextString(String root, String next) {
        int diff = 0;
        int len = root.length();
        for (int i = 0; i < len; i ++) {
            if (root.charAt(i) != next.charAt(i)) {
                diff ++;
            }

            if (diff > 1) {
                return false;
            }
        }

        if (diff == 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        Trans trans = new Trans();
        System.out.println(trans.ladderLength(beginWord, endWord, wordList));
    }
}