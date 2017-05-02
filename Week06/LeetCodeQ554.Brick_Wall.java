/*
554. Brick Wall
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. 
The bricks have the same height but different width. 
You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of 
each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. 
You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, 
in which case the line will obviously cross no bricks.

Example:
Input: 
[[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
Output: 2
*/

public class Solution {
    //思路是遍历每一行得到前项和,用hashmap来统计前n项和的个数
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0 || wall.get(0).size() == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            //You cannot draw a line just along one of the two vertical edges of the wall
            for (int i = 0; i < row.size() - 1; i++) { //这里一定是row.size() - 1,不能算到最后一行，因为肯定是0
                sum += row.get(i);
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        int result = wall.size();
        for (int key : map.keySet()) {
            result = Math.min(result, wall.size() - map.get(key));
        }
        return result;
    }
    
    /************************************************************************************************************/
    //implement with java 8 new feature, map.getOrDefault(key, defaultValue)
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0 || wall.get(0).size() == 0) {
            return 0;
        }
        int count = 0; //用于统计最大的相同的sum end的位置有多少块
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) { //*****计算前n项和不能到最后一块
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                count = Math.max(count, map.get(sum));
            }
        }
        return wall.size() - count; //墙的高度减去之前统计的最大的相同砖块的end的位置，就是最少cross砖块的数量
    }
}
