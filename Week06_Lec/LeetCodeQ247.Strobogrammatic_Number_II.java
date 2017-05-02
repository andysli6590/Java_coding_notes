/*
247. Strobogrammatic Number II
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].
*/

public class Solution {
    private static  final String[][] pairs = {{"0", "0"}, {"1", "1"}, {"6", "9"}, {"9", "6"}, {"8", "8"}};
    public List<String> findStrobogrammatic(int n) {
        return findStrobogrammatic(n, n);
    }
    
    private List<String> findStrobogrammatic(int curLen, int len) {
        //corner case
        //for len is even cases
        if (curLen == 0) {
            return new ArrayList<String>(Arrays.asList(""));
        }
        //for len is odd cases
        if (curLen == 1) {
            return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        }
        //recursive get last layer result
        List<String> next = findStrobogrammatic(curLen - 2, len);
        //create current layer result
        List<String> cur = new ArrayList<>();
        
        for (String s : next) {
            for (String[] p : pairs) {
                if (curLen == len && p[0] == "0") {
                    continue; //remove the cases like "000", "010" ...
                }
                cur.add(p[0] + s + p[1]);
            }
        }
        return cur;
    }
}
