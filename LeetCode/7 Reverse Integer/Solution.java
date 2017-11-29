class Solution {
    public int reverse(int x) {
        int result;

        if (x > 0){
            try{result = Integer.parseInt(reverseString(x));}
            catch (NumberFormatException e) {return 0;}
        }
        else {
             try{result = Integer.parseInt("-" + reverseString(-x));}
            catch (NumberFormatException e) {return 0;}
        }
        return result;
    }

    public String reverseString(int s){
        String str = Integer.toString(s);
        StringBuilder sb = new StringBuilder(str);
        return new String(sb.reverse());
    }
}
