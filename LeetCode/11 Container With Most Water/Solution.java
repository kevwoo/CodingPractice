class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxStorage = 0;
        int x, y;

        while (left < right){
            x = (right - left);
            y = Math.min(height[left], height[right]);
            maxStorage = Math.max(maxStorage, x * y);
            if (height[right] > height[left])
                left++;
            else
                right--;
        }
        return maxStorage;
    }
}
