class Solution {
    public int lengthOfLongestSubstring(String s1) {
    // Sliding Window Algorithm
        //String s1 = "pwwkew";
        HashMap<Character, Integer> testTable = new HashMap<>();
        int max_length = 0;
        int index = 0;
        int curr_length = 0;
        int head = 0;
        for (int i=0; i<s1.length(); i++){
            if (!testTable.containsKey(s1.charAt(i))){
                curr_length++;
                if (max_length <= curr_length)
                    max_length = curr_length;
                testTable.put(s1.charAt(i), i);
                //System.out.println(s1.charAt(i) + "~found" + "length: " +  curr_length + " max: " + max_length);
            }
            else{
                head = index;
                index = Math.max(head, testTable.get(s1.charAt(i)));
                //System.out.println("head: " + head + " index: " + index);
                curr_length = i - index;
                //System.out.println(s1.charAt(i) + "found" + "length: " + curr_length + " max: " + max_length);
                if (max_length <= curr_length)
                    max_length = curr_length;
                testTable.put(s1.charAt(i), i);
            }      
        }  
        return max_length;
    }
}
