/*
524. Longest Word in Dictionary through Deleting
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by 
deleting some characters of the given string. If there are more than one possible results, 
return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.

*/

public class Solution {
    //to sort the dictionary from long to short length word, and scan through to check if it is a subsequence of source string
    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0 || d == null || d.size() == 0) {
            return "";
        }
        
        //lamda express
        Collections.sort(d, (s1, s2) -> s1.length() != s2.length() ? -Integer.compare(s1.length(), s2.length()) : s1.compareTo(s2)); 
        for (String  word : d) {
            int i = 0;
            for (char ch : s.toCharArray()) {
                if (i < word.length() && ch == word.charAt(i)) {
                    i++;
                }
                if (i == word.length()) {
                    return word;
                }
            }
        }
        return "";
    }
    
    /*********************************************************************************************************************************/
    //optimized implementation without sorting the dictionary
    public String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0 || d == null || d.size() == 0) {
            return "";
        }
        String longest = "";
        for (String dictWord : d) {
            int i = 0;
            for (char ch : s.toCharArray()) {
                if (i < dictWord.length() && ch == dictWord.charAt(i)) {
                    i++;
                }
            }
            if (i == dictWord.length() && dictWord.length() >= longest.length()) {
                if (dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0) {
                    longest = dictWord;
                }
            }
        }
        return longest;
    }
}
