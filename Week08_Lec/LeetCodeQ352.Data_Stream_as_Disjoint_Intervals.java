/*
352. Data Stream as Disjoint Intervals
Given a data stream input of non-negative integers a1, a2, ..., an, ..., 
summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
*/


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class SummaryRanges {
    //implementation using TreeMap, time complexity: O(logN)
    private TreeMap<Integer, Interval> tree; 
    //key is the interval start, value is the interval itself
    /** Initialize your data structure here. */
    public SummaryRanges() {
        tree = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (tree.containsKey(val)) {
            return;
        }
        Integer lower = tree.lowerKey(val);
        Integer higher = tree.higherKey(val);
        if (lower != null && tree.get(lower).end == val - 1 && higher != null && higher == val + 1) {
            tree.get(lower).end = tree.get(higher).end;
            tree.remove(higher);
        } else if (higher != null && higher == val + 1) {
            tree.put(val, new Interval(val, tree.get(higher).end));
            tree.remove(higher);
        } else if (lower != null && tree.get(lower).end >= val - 1) {
            tree.get(lower).end = Math.max(tree.get(lower).end, val);
        } else {
            tree.put(val, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<>(tree.values());
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
