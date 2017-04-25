/*
291. Word Pattern II
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
*/

public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return isMatch(str, 0, pattern, 0, map, set);
    }
    //dfs, back tracking
    private boolean isMatch(String str, int i, String pattern, int j, Map<Character, String> map, Set<String> set) {
        //base case 
        if (i == str.length() && j == pattern.length()) {
            return true;
        }
        if (i == str.length() || j == pattern.length()) {
            return false;
        }
        
        //get current pattern character
        char ch = pattern.charAt(j);
        
        //if the pattern character exists
        if (map.containsKey(ch)) {
            String s = map.get(ch);
            
            //then check if we can use it to match str[i...i + s.length()] to s
            if (!str.startsWith(s, i)) {
                return false;
            }
            
            //if it can match, continue to match the rest
            return isMatch(str, i + s.length(), pattern, j + 1, map, set);
        }
        
        //if the pattern character does not exist int he map
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1); //inclusive and exclusive
            
            if (set.contains(p)) {
                continue;
            }
            
            //create or update map and set
            map.put(ch, p);
            set.add(p);
            
            if (isMatch(str, k + 1, pattern, j + 1, map, set)) {
                return true;
            }
            
            //back tracking, remove current p from map and set
            map.remove(ch);
            set.remove(p);
        }
        
        return false;
    }
}
