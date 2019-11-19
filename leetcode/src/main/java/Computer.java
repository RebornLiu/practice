import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 * @author by liuweiliang1
 * @date 2019/11/7 21:45
 * @description
 */
public class Computer {

    public int caculator(String str) {
        Stack<Integer> numbers = new Stack<>();

        boolean need = false;
        for (int i = 0; i < str.length(); i ++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                if (numbers.isEmpty()) {
                    numbers.push(ch - '0');
                }
                else {
                    Integer top = numbers.pop();
                    if (top != null && top >= '0' && top <= '9') {
                        numbers.push(top * 10 + ch - '0');
                    } else {
                        numbers.push(ch - '0');
                    }
                }
            }

            else {
                if (need) {
                    int top = numbers.pop();
                    char op = (char)numbers.pop().intValue();
                    int sec = numbers.pop();
                    numbers.push(cal(sec, top, op));
                    need = false;
                }
                else {
                    numbers.push((int)ch);
                    if (ch == '*' || ch == '/') {
                        need = true;
                    }
                }
            }
        }

        if (numbers.size() >= 3) {
            int top = numbers.pop();
            char op = (char)numbers.pop().intValue();
            int sec = numbers.pop();
            return cal(sec, top, op);
        }
        else {
            return numbers.pop();
        }
    }


    private int cal(int a, int b, char op) {
        if (op == '-') {
            return a - b;
        }
        else if (op == '+') {
            return a + b;
        }
        else if (op == '*') {
            return a * b;
        }
        else if (op == '/') {
            return a / b;
        }

        throw new IllegalArgumentException(JSON.toJSONString(op));
    }

    public static void main(String[] args) {
        Computer c = new Computer();
        System.out.println(c.caculator("4+5/6*7-1"));
    }
}
