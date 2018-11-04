import java.util.Stack;

public class MyQueueByStack {

    public static void main(String[] args) {
        MyQueueByStack obj = new MyQueueByStack();
        obj.push(1);
        obj.push(2);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();
    }

    private Stack<Integer> integerStack = new Stack<>();

    /** Initialize your data structure here. */
    public MyQueueByStack() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (empty()) {
            integerStack.push(x);
            return;
        }

        int top = integerStack.pop();
        push(x);
        integerStack.push(top);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return integerStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return integerStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return integerStack.isEmpty();
    }
}
