/*
346. Moving Average from Data Stream
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

public class MovingAverage {
    
    private Deque<Integer> queue;
    private int sum;
    private int size;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.queue = new LinkedList<>();
        this.sum = 0;
        this.size = size;
    }
    
    public double next(int val) {
        if (queue.size() == size) {
            //poll from head
            int first = queue.pollFirst();
            sum -= first;
        }
        queue.offerLast(val); //offer from tail
        sum += val;
        return sum  * 1.0 / queue.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
