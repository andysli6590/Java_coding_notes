/*
444. Sequence Reconstruction
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. 
Reconstruction means building a shortest common supersequence of the sequences in seqs 
(i.e., a shortest sequence so that all sequences in seqs are subsequences of it). 
Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, 
because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true
*/

public class Solution {
    //build up the graph and BFS to validate
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (org == null || org.length == 0 || seqs == null || seqs.size() == 0) {
            return false;
        }
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        Map<Integer, Integer> inCount = new HashMap<>();
        
        //construct graph
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                //Add node
                graph.putIfAbsent(seq.get(i), new ArrayList<Integer>());
                inCount.putIfAbsent(seq.get(i), 0);
                //Add neighbors
                if (i != 0) {
                    //prev node has current node as neighbor
                    graph.get(seq.get(i - 1)).add(seq.get(i)); 
                    inCount.put(seq.get(i), inCount.get(seq.get(i)) + 1);
                }
            }
        }
        //pre-check with length
        if (org.length != inCount.size()) {
            return false;
        }
        
        Deque<Integer> queue = new ArrayDeque<>();
        //generate next -> inCount == 0
        for (Integer key : inCount.keySet()) {
            if (inCount.get(key) == 0) {
                queue.offerLast(key);
            }
        }
        
        int index = 0;
        //Always only one logical starter node!!
        while (queue.size() == 1) {
            int cur = queue.pollFirst();
            //order in org
            if (org[index++] != cur) {
                return false;
            }
            for (int next : graph.get(cur)) {
                int count = inCount.get(next);
                if (--count == 0) {
                    queue.offerLast(next);
                }
                inCount.put(next, count);
            }
        }
        return index == org.length;
    }
}

