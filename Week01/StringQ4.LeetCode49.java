/*
49. Group Anagrams
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
*/

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) return result;
        HashMap<String, List<String>> mp = new HashMap<>();
        for (String str : strs) {
            String key = sortString(str);
            if (mp.containsKey(key)) {
                mp.get(key).add(str);
            } else {
                mp.put(key, new ArrayList<>());
                mp.get(key).add(str);
            }
        }
        return new ArrayList<List<String>>(mp.values());
    }
    
    private String sortString(String str) {
        if (str == null || str.length() == 0) return "";
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}