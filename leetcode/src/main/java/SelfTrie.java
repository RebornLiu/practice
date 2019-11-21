import java.util.ArrayList;
import java.util.List;

public class SelfTrie {

    private TrieNode root;

    public SelfTrie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        TrieNode preNode = root;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            List<TrieNode> nextes = preNode.next;
            TrieNode n = contain(nextes, word.charAt(i));
            if (n == null) {
                this.addTail(preNode, word.substring(i));
                return;
            }
            else {
                preNode = n;
            }
        }

        preNode.tail = true;
    }

    private void addTail(TrieNode pre, String word) {
        int len = word.length();
        for (int i = 0; i < len; i ++) {
            List<TrieNode> nextes = pre.next;
            if (nextes == null) {
                nextes = new ArrayList<>();
                pre.next = nextes;
            }
            TrieNode trieNode = new TrieNode();
            trieNode.ch = word.charAt(i);
            nextes.add(trieNode);
            pre = trieNode;
        }

        pre.tail = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        TrieNode pre = root;
        int len = word.length();
        for (int i = 0; i < len; i ++) {
            List<TrieNode> nodes = pre.next;
            TrieNode n = this.contain(nodes, word.charAt(i));
            if (n == null) {
                return false;
            }
            else {
                pre = n;
            }
        }

        return pre.tail;
    }

    private TrieNode contain(List<TrieNode> nodes, char ch) {
        if (nodes == null) {
            return null;
        }

        for (TrieNode node : nodes) {
            if (node.ch == ch) {
                return node;
            }
        }

        return null;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        TrieNode pre = root;
        int len = word.length();
        for (int i = 0; i < len; i ++) {
            List<TrieNode> nodes = pre.next;
            TrieNode n = this.contain(nodes, word.charAt(i));
            if (n == null) {
                return false;
            }
            else {
                pre = n;
            }
        }

        return true;
    }

    private static class TrieNode {
        public char ch;
        public List<TrieNode> next;
        public boolean tail;
    }
}
