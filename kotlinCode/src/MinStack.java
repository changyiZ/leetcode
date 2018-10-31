import java.util.ArrayList;
import java.util.List;

class MinStack {

    private List<Integer> elements = new ArrayList();
    private int min = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStack() {
    }

    public void push(int x) {
        if (x <= min) {
            // Staging last min value.
            elements.add(min);
            min = x;
        }
        elements.add(x);
    }

    public void pop() {
        int lastIndex = elements.size() - 1;
        int top = elements.get(lastIndex);
        elements.remove(lastIndex);
        if (min == top) {
            min = elements.get(lastIndex - 1);
            elements.remove(lastIndex - 1);
        }
    }

    public int top() {
        return elements.get(elements.size() - 1);
    }

    public int getMin() {
        return min;
    }
}