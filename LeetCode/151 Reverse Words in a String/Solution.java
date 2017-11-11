public class Solution {
    public String reverseWords(String s) {
        if (s.length() == 0 || s.trim().equals(""))
            return "";
        else if (s.length() == 1)
            return s;
        String[] ans = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=ans.length-1; i>-1; i--){
            if (i == 0)
                if (ans[i].equals(" "))
                    continue;
                else    sb.append(ans[i]);
            else{
                if (ans[i-1].equals(""))
                    sb.append(ans[i]);
                else{
                    sb.append(ans[i]);
                    sb.append(" ");
                }
            }
            
        }
        
        return sb.toString();
    }
}
