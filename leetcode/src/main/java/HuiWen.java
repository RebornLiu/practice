public class HuiWen {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }
        
        int len = s.length();
        if (len == 1) {
            return true;
        }
        
        int head = 0;
        int tail = len - 1;
        
        while (head < tail) {
            char headC = s.charAt(head);
            if (!illegalChar(headC)) {
                head ++;
                continue;
            }
            char tailC = s.charAt(tail);
            if (!illegalChar(tailC)) {
                tail --;
                continue;
            }

            if (!equalChar(tailC, headC)) {
                return false;
            }
            head ++;
            tail --;
        }

        return true;
    }

    private boolean illegalChar(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }

        if (ch >= 'a' && ch <= 'z') {
            return true;
        }

        if (ch >= 'A' && ch <= 'Z') {
            return true;
        }
        
        return false;
    }

    private boolean equalChar(char c1, char c2) {
        int smallC1 = c1;
        if (c1 >= 'A' && c1 <= 'Z') {
            smallC1 = c1 + ('a' - 'A');
        }

        int smallC2 = c2;
        if (c2 >= 'A' && c2 <= 'Z') {
            smallC2 = c2 + ('a' - 'A');
        }

        return smallC2 == smallC1;
    }

    public static void main(String[] args) {
        HuiWen huiWen = new HuiWen();
        System.out.println(huiWen.isPalindrome("race a car"));
    }
}