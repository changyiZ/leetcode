import java.util.LinkedList;
import java.util.Queue;

public class MyStackByQueue {

    private Queue<Integer> integerQueue = new LinkedList<>();

    /** Initialize your data structure here. */
    public MyStackByQueue() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        int size = integerQueue.size();
        integerQueue.add(x);
        int data;
        while (size-- > 0) {
            integerQueue.add(integerQueue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return integerQueue.poll();
    }

    /** Get the top element. */
    public int top() {
        return integerQueue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return integerQueue.isEmpty();
    }
}
