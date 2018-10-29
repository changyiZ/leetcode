class MyCircularQueue(private val capacity: Int) {

    /** Initialize your data structure here. Set the size of the queue to be k. */
    private var head = -1
    private var tail = -1
    private val dataArray = IntArray(capacity)

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    fun enQueue(value: Int): Boolean {
        if (isFull()) {
            return false
        }
        tail = (tail + 1) % capacity
        dataArray[tail] = value
        if (isEmpty()) {
            head = 0
        }
        return true
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    fun deQueue(): Boolean {
        if (isEmpty()) {
            return false
        }
        if (head == tail) {
            head = -1
            tail = -1
        }
        head = (head + 1) % capacity
        return true
    }

    /** Get the front item from the queue. */
    fun Front(): Int {
        if (head < 0) {
            return -1
        }
        return dataArray[head]
    }

    /** Get the last item from the queue. */
    fun Rear(): Int {
        if (tail < 0) {
            return -1
        }
        return dataArray[tail]
    }

    /** Checks whether the circular queue is empty or not. */
    fun isEmpty(): Boolean {
        return head == -1
    }

    /** Checks whether the circular queue is full or not. */
    fun isFull(): Boolean = (tail + 1) % capacity == head

}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * var obj = MyCircularQueue(k)
 * var param_1 = obj.enQueue(value)
 * var param_2 = obj.deQueue()
 * var param_3 = obj.Front()
 * var param_4 = obj.Rear()
 * var param_5 = obj.isEmpty()
 * var param_6 = obj.isFull()
 */