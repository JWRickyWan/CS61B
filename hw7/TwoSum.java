import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.Stack;

import java.util.HashMap;

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> temp = new HashMap<>();
        int[] ans = new int[2];
        for(int i =0;i<nums.length;i++){
            int diff = target - nums[i];
            if(!temp.containsKey(diff)){
                temp.put(nums[i],i);
            }
            Queue<Integer> x = new Queue<>();
            Stack<Character> stack= new Stack<>();
            else{
                ans[0] = i;
                ans[1] = temp.get(i);
                return ans;}
        }
        return  null;

    }
}
