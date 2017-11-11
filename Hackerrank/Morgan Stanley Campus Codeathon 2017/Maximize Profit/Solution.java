/*
Crypto Bank provides n crypto currencies, each of which has a fixed conversion rate with respect to dollar. The conversion rate w.r.t dollar is not subject to change over time. But as you may have imagined, the conversion rate between each pair of crypto currencies may change over time, due to economic and political factors.

Ron has m bitcoins with him. The conversion rate of a bitcoin w.r.t dollar is k. You are given the conversion rates of every other crypto currency with respect to bitcoin. You are also given the conversion rate of these crypto currencies w.r.t dollar. You have to find the currency, that Ron can buy with m bitcoins, such that the total value in dollars is maximized.

Note that, Ron cannot buy crypto currency in fractions and Ron can own only one type of crypto currency at the end of the transaction.

Output Format

In a single line, you have to output the maximum value in dollars that you can have. It is guaranteed that the answer will fit into a 32-bit integer.

Sample Input 0

5 10 3
2 3 4 1 5
1 3 2 4 5
Sample Output 0

250
Explanation 0

If you keep the bitcoins you have, then the value in dollars is 30. If you decide to buy the 1st currency, then you can have 10*1 = 10 units of that currency, and the value in dollars is 10*2 = 20. Similarly, the values in dollars of the 2nd, 3rd and 4th currency that you can buy are 90, 80, and 40 respectively. If you decide to buy the 5th currency, then you can have a total value in dollars of 250. This is greater than any other value and therefore the output is 250.

Sample Input 1

10 1 1000
1 2 3 4 5 6 7 8 9 10
10 9 8 7 6 5 3 2 1 1
Sample Output 1

1000
Explanation 1

The total value in dollars of the bitcoin is 1 * 1000 = 1000. This is greater than the value in dollars of any other currency which are 10, 18, 24, 28, 30, 30, 21, 16, 9 and 10 respectively. Therefore, the output is 1000.



*/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int maximizeProfit(int[] a, int[] b, int m, int k) {
        // Complete this function
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for(int a_i = 0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int[] b = new int[n];
        for(int b_i = 0; b_i < n; b_i++){
            b[b_i] = in.nextInt();
        }
        int result = maximizeProfit(a, b, m, k);
        System.out.println(result);
        in.close();
    }
}
