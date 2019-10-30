import java.util.Stack;

public class RPN {
    public int evalRPN(String[] tokens) {
        if (tokens == null) {
            return -1;
        }

        int len = tokens.length;
        if (len == 0) {
            return -1;
        }

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(convertToInt(token));
            }
            else {
                Integer second = stack.pop();
                Integer first = stack.pop();
                stack.push(compute(first, second, token));
            }
        }

        return stack.pop();
    }

    private Integer compute(Integer first, Integer second, String str) {
        if ("+".equals(str)) {
            return first + second;
        }

        if ("-".equals(str)) {
            return first - second;
        }

        if ("*".equals(str)) {
            return first * second;
        }

        if ("/".equals(str)) {
            return first / second;
        }

        return 0;
    }

    private Integer convertToInt(String str) {
        if (str.startsWith("-")) {
            return 0 - Integer.valueOf(str.substring(1));
        }
        else if (str.startsWith("+")) {
            return Integer.valueOf(str.substring(1));
        }

        return Integer.valueOf(str);
    }

    private boolean isNumber(String str) {
        if ("+".equals(str)) {
            return false;
        }

        if ("-".equals(str)) {
            return false;
        }

        if ("*".equals(str)) {
            return false;
        }

        if ("/".equals(str)) {
            return false;
        }

        return true;
    }
}
