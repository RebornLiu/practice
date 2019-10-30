import java.util.Stack;

public class MinStack {

    private Stack<Integer> container = new Stack<>();
    private Stack<Integer> helper = new Stack<>();

    public MinStack() {

    }

    public void push(int x) {
        container.push(x);
        if (helper.empty()) {
            helper.push(x);
        }
        else {
            helper.push(Math.min(helper.peek(), x));
        }
    }

    public void pop() {
        container.pop();
        helper.pop();
    }

    public int top() {
        return container.peek();
    }

    public int getMin() {
        return helper.peek();
    }
}
