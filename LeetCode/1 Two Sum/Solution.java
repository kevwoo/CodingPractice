import java.util.HashMap;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> complementSet = new Hashtable<>();
        // Traverse the array
        int complement;
        for (int i=0; i<nums.length; i++){
            complement = target - nums[i];
            if (complementSet.containsKey(complement)){
                return new int[] {complementSet.get(complement), i};
            }
            else
                complementSet.put(nums[i], i);
        }
        // if not found return null
        return null;
    }
}
