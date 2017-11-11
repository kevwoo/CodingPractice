/*Twitter Code Challenge:
 * Information Masking
 * When working with data from Twitter's users, engineers sometimes need to look at the data to understand whether out software is working correctly.
 * In doing so, it's important to maks users' personally identifiable information (PII) which the engineer does not have a need to know.
 * In this problem, you'll write a program to mask email address and phone number in a human friendly way.
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;
        while ((inputLine = br.readLine()) != null){

            if (inputLine.charAt(0) == 'E')
                System.out.println(emailTrim(inputLine));
            else if (inputLine.charAt(0) == 'P')
                System.out.println(phoneTrim(inputLine));
        }
        br.close();
    }

    public static String emailTrim(String s){
            StringBuilder sb = new StringBuilder("E:");
            String error = ("E: invalid input");
            int dotCount = 0;
            if(s.charAt(2) == '.')
                return new String(error);
            else
                sb.append(s.charAt(2));

            int size = s.length();
            int index = 0;
            for (int i = 3; i<size; i++){
                if (s.charAt(i) == '.')
                    dotCount++;
                if (dotCount > 1)
                    return new String(error);
                if (s.charAt(i) == '@'){
                    index = i-1;
                    break;
                }
            }

            sb.append("*****");
            sb.append(s.substring(index, size));;
            return sb.toString();
        }

    public static String phoneTrim(String s){
            StringBuilder sb = new StringBuilder("P:");
            String error = "P: Invalid Input";
            int index = 2;
            int numberCount = 0;
            int size = s.length();
            boolean hasPlus = (s.charAt(index) == '+') ? true : false;
            if (hasPlus){
                for (int i = 2; i<size; i++){
                    if (s.charAt(i) == ' '){
                        index = i;
                        break;
                    }
                    else if (s.charAt(i) == '('){
                        index = i;
                        break;
                    }
                    else{
                        if (s.charAt(i) == '+')
                            sb.append('+');
                        else{
                        numberCount++;
                        sb.append('*');
                        }
                    }
                }
                sb.append('-');
            }
            int counter = 0;
            int counterCounter = 0;
            char sample;
            for (int i = index; i<size; i++){
                sample = s.charAt(i);
                if (counter == 3){
                    sb.append('-');
                    counter = 0;
                    counterCounter++;
                }
                switch(sample){
                    case '+':
                        hasPlus = true;
                        continue;
                    case '(':
                        continue;
                    case ')':
                        continue;
                    case '-':
                        continue;
                    case ' ':
                        continue;

                    default:
                        if (counterCounter < 2){
                            sb.append('*');
                            counter++;
                            numberCount++;
                        }
                        else{
                            sb.append(sample);
                            numberCount++;
                        }
                }//end of switch
            }//end of for loop
            if ((hasPlus && numberCount < 10 && numberCount > 13) || (!hasPlus && numberCount != 10))
                return new String(error);
            return sb.toString();
        }// end of function
}
