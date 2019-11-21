import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class BaseComputer {

    /**
     * 如果是数字 直接放在suffix列表
     * 如果是(入栈
     * 如果是) 将栈顶元素依次出栈放入suffix，直到遇到(, (不进入suffix
     * 如果是运算符：
     *          a.如果运算符优先级小于栈顶运算符，则栈顶元素一次出栈进入suffix，且当前运算符入栈
     *          b。如果运算符优先级大于栈顶运算符，则入栈
     * */
    private String convertToSuffix(String express) {
        List<Character> suffix = new ArrayList<>(express.length());
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < express.length(); i ++) {
            char ch = express.charAt(i);
            if (isDigit(ch)) {
                suffix.add(ch);
            }
            else {
                if (ch == '(') {
                    stack.push(ch);
                }
                else if (ch == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        suffix.add(stack.pop());
                    }
                    stack.pop();
                }
                else {
                    if (!stack.isEmpty()) {
                        if (stack.peek() != '(' && levelOp(stack.peek()) > levelOp(ch)) {
                            while (!stack.isEmpty() && stack.peek() != '(') {
                                suffix.add(stack.pop());
                            }
                        }
                    }
                    stack.push(ch);
                }
            }
        }

        while (!stack.isEmpty()) {
            suffix.add(stack.pop());
        }

        return suffix.stream().map(Object::toString).collect(Collectors.joining());
    }


    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private int levelOp(char ch) {
        if (ch == '*') {
            return 2;
        }

        if (ch == '/') {
            return 2;
        }

        if (ch == '-') {
            return 1;
        }

        if (ch == '+') {
            return 1;
        }

        throw new IllegalArgumentException("hahha");
    }

    public static void main(String[] args) {
        BaseComputer baseComputer = new BaseComputer();
        System.out.println(baseComputer.convertToSuffix("1+2*3+(4*5+6)*7"));

    }
}
