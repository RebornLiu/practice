import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidKuohao {

    public boolean isValid(String s) {
        if (s == null) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
         for (int i = 0; i < s.length(); i ++) {
             char c = s.charAt(i);
             if (c == ')') {
                 if (stack.isEmpty()) {
                     return false;
                 }
                 if (stack.peek() != '(') {
                     return false;
                 }

                 stack.pop();
                 continue;
             }

             if (c == ']') {
                 if (stack.isEmpty()) {
                     return false;
                 }
                 if (stack.peek() != '[') {
                     return false;
                 }

                 stack.pop();
                 continue;
             }

             if (c == '}') {
                 if (stack.isEmpty()) {
                     return false;
                 }
                 if (stack.peek() != '{') {
                     return false;
                 }

                 stack.pop();
                 continue;
             }

             stack.push(c);
         }

         if (!stack.isEmpty()) {
             return false;
         }

        return true;
    }

    public static void main(String[] args) {
        ValidKuohao validKuohao = new ValidKuohao();
        System.out.println(validKuohao.isValid("([)]"));
    }
}
