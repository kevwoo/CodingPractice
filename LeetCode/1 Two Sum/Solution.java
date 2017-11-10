import java.util.Hashtable;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Hashtable<Integer, Integer> complementSet = new Hashtable<>();
        int[] ans = new int[2];
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
