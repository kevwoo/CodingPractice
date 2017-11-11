/*
Given a list of n strings, each consisting of digits and spaces, the number of spaces is the same for each entry, the goal is to implement a variation of a sort command. None of the strings contains consecutive spaces. Also, no string starts with a space nor ends with it. Spaces are used to divide string into columns, which can be used as keys in comparisons.

The program has to support the required parameters:

key: integer denoting the column used as a key in comparisons. The left-most column is denoted by .

reversed: boolean variable indicating whether to reverse the result of comparisons

comparison-type: either lexicographic or numeric. Lexicographic means that we use Lexicographical order where for example 122 < 13. Numeric means that we compare the strings by their numerical values, so 13 < 122. If the comparison type is numeric and numeric values of keys of s1 and s2 are equal for i < j , then s1 is considered strictly smaller than s2 because it comes first.

Input Format

In the first line, there is a single integer n denoting the number of strings to sort. In the i-th of the following n lines there is a string s(i). In the last line, there are 3 space-separated values, denoting the values of variables key, reversed, comparison-type.

Sample Input 0

3
122
12
13
1 false lexicographic
Sample Output 0

12
122
13
Explanation 0

There is only 1 key, reversal is false and the order of comparison is lexicographic. Therefore, the output is 12, 122 and 13.

Sample Input 1

3
122
12
13
1 true lexicographic
Sample Output 1

13
122
12
Explanation 1

There is only 1 key, reversal is true and comparison is lexicographic. The lexicographic order is 12, 122, 13. Therefore, the output is the reverse of this which is 13, 122, 12.

Sample Input 2

3
92 022
82 12
77 13
2 false numeric
Sample Output 2

82 12
77 13
92 022
Explanation 2

The key for ordering is 2, reversal is false and ordering is numeric. Therefore, the keys 022, 12 and 13 should be ordered as 12, 13 and 022. Therefore, the final output is 82 12, 77 13 and 92 022.

*/

import java.io.*;
import java.util.*;

public class Solution {
    static final int LEXICOGRAPHIC = 1;
    static final int NUMERIC = 2;

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;

        //First Command
        inputLine = br.readLine();
        int arraySize = Integer.parseInt(inputLine);
        ArrayList<Node> nodeArray = new ArrayList<>(arraySize);

        //Second Command: data feeding
        for (int i=0; i<arraySize; i++){
            inputLine = br.readLine();
            nodeArray.add(new Node(inputLine));
        }

        //Third Command: Instructions
        inputLine = br.readLine();
        String[] analyzeString = inputLine.split(" ");
        int index = Integer.parseInt(analyzeString[0])- 1;
        boolean inOrder = (analyzeString[1].equals("true")) ? false : true;
        int orderMode = (analyzeString[2].equals("lexicographic"))? LEXICOGRAPHIC : NUMERIC;
        for (Node nodes : nodeArray)
            nodes.update(index);


        displayAnswer(nodeArray, inOrder, orderMode);
    }

    static void displayAnswer(ArrayList<Node> nodeArray, boolean inOrder, int orderMode){
        if (orderMode == LEXICOGRAPHIC){
            Collections.sort(nodeArray, new SortbyWord());
            if (!inOrder)
                Collections.reverse(nodeArray);
            displayArray(nodeArray);
        }
        else if (orderMode == NUMERIC){
            Collections.sort(nodeArray, new SortbyNumber());
            if (!inOrder)
                Collections.reverse(nodeArray);
            displayArray(nodeArray);
        }
    }

    static void displayArray(ArrayList<Node> ansArray){
        for (Node entry : ansArray){
            entry.displayNode();
        }
    }

    public static class Node{
        String input;
        String word;
        int number;
        String[] inputFeed;

        public Node(String input){
            this.input = input;
        }

        public void update(int index){
            inputFeed = input.split(" ");
            word = inputFeed[index];
            number = Integer.parseInt(inputFeed[index]);
        }

        void displayNode(){
            for (int i=0; i<inputFeed.length; i++)
                System.out.print(inputFeed[i] + " ");
            System.out.println();
        }
    }

    static class SortbyWord implements Comparator<Node>{
        public int compare(Node a, Node b){
            return a.word.compareTo(b.word);
        }
    }

    static class SortbyNumber implements Comparator<Node>{
        public int compare(Node a, Node b){
            return a.number - b.number;
        }
    }
}
