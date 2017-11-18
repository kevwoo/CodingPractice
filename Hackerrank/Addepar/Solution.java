/* The Problem:
 * Reconciliation is a term Addepar uses for a set of correctness and consistency measures applied to the data
 * we receive and use in financial calculations.
 *
 * One of the most common reconciliation checks is called unit reconciliation, which answers the question, "does the
 * transaction history add up to the number of shares the bank says I have?" For example, if the bank said I had 100 shares
 * of Apple at the end of yesterday, and I bought 20 shares of Apple today, then we expect the bank to report 120 shares
 * at the end of today. This surprisingly isn't always the case! The bank may send incomplete data, we may be parsing it
 * incorrectly, or there may be events like corporate actions or trade settlement lag that cause an inconsistency.
 *
 * Unit reconciliation is very important, because numbers that don't add up shouldn't be trusted for any metrics.
 *
 * The Input:
 * recon.in has three sections:
 * 1. D0-POS describes the positions in the account at the end of Day 0. Each record is a space-separated pair of Symbol
 * and Shares. For example, "AAPL 10" means 10 shares of AAPL were held at the end of Day 0, and "Cash 122.16" means we
 * had $122.16 in the account at the end of Day 0.
 * 2. D1-TRN describes the transactions that occurred in the account on Day 1. Each record is space-separated collection of four items:
 * Symbol, Transaction Code, Shares, and Total Value. For example, the record "AAPL BY 10 6123.21" means 10 shares of AAPL
 * were bought for a total cost of $6123.21.
 * 3. D1-POS describes the positions in the account at the end of Day 1, and has the same format as D0-POS.
 *
 * The Output:
 * The objective is to write a program that prints an alphabetically ordered, space-separated record for each position that
 * fails unit reconciliation. For example, "AAPL 10" means that the reported shares of AAPL in D1-POS is 10 higher than expected
 * based on transactions.
 * The outputs must be printed in alphabetical order.
 *
 *
 *
 */

/*
    Sample Input 1:
        13
        D0-POS
        AAPL 100
        GOOG 200
        Cash 10

        D1-TRN
        AAPL SL 50 30000
        GOOG BY 10 10000

        D1-POS
        AAPL 50
        GOOG 220
        Cash 20000

     Sample Output 1:
        Cash -10
        GOOG 10

     Sample Input 2:
         14
         D0-POS
         AAPL 100
         GOOG 200
         Cash 10

         D1-TRN
         AAPL SL 50 30000
         GOOG BY 10 10000

         D1-POS
         AAPL 50
         GOOG 220
         Cash 20000
         MSFT 10

     Sample Output 2:
        Cash -10
        GOOG 10
        MSFT 10

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static void reconcile(String[] fileContents) {
        int i = 0;
        int size = Integer.parseInt(fileContents[i++]);
        PriorityQueue<Node> nodePQ = new PriorityQueue<>(10, new PQsort());
        HashMap<String, Node> nodeMap = new HashMap<>();
        double stockCount = 0;
        double cashAmount = 0;

        if (fileContents[i++].equals("D0-POS")) {
            while (!fileContents[i].equals("")) {
                String[] inputArray = fileContents[i].split(" ");
                nodeMap.put(inputArray[0], new Node(inputArray[0], Double.parseDouble(inputArray[1])));
                i++;
            }
        }
        i++;

        if (fileContents[i++].equals("D1-TRN")) {
            while (!fileContents[i].equals("")) {
                String[] inputArray = fileContents[i].split( " ");
                Node temp;
                if (nodeMap.containsKey(inputArray[0])) {
                    temp = nodeMap.get(inputArray[0]);
                }
                else {
                    temp = new Node(inputArray[0], 0);
                    nodeMap.put(temp.code, temp);
                }
                Node cash = nodeMap.get("Cash");

                if (inputArray[1].equals("BY")) {
                    stockCount = Double.parseDouble(inputArray[2]);
                    cashAmount = Double.parseDouble(inputArray[3]) * -1;
                }
                else if (inputArray[1].equals("SL")){
                    stockCount = Double.parseDouble(inputArray[2]) * -1;
                    cashAmount = Double.parseDouble(inputArray[3]);
                }
                temp.shareAmount += stockCount;
                cash.shareAmount += cashAmount;

                nodeMap.replace(inputArray[0], temp);
                nodeMap.replace("Cash", cash);
                i++;
            }
        }
        i++;

        if (fileContents[i++].equals("D1-POS")) {
            while (!fileContents[i].equals("")) {
                String[] inputArray = fileContents[i].split(" ");
                if (nodeMap.containsKey(inputArray[0])) {
                    Node temp = nodeMap.get(inputArray[0]);
                    double realVal = temp.shareAmount;
                    double bankVal = Double.parseDouble(inputArray[1]);
                    if (realVal != bankVal)
                        temp.answer = bankVal - realVal;
                }
                else{
                    Node errorNode = new Node(inputArray[0], Double.parseDouble(inputArray[1]));
                    errorNode.answer = errorNode.shareAmount;
                    nodePQ.add(errorNode);
                }
                i++;
                if (i > size)
                    break;
            }
        }

        for (Node aNode: nodeMap.values()) {
            if (aNode.answer != 0.0)
                nodePQ.add(aNode);
        }

        while (!nodePQ.isEmpty()){
            Node temp = nodePQ.poll();
            System.out.println(temp.code + " " + (int) temp.answer);
        }
    }

    static class Node{
        String code;
        double shareAmount;
        double answer = 0;
        Node(String code, double shareAmount){
            this.code = code;
            this.shareAmount = shareAmount;
        }
    }

    static class PQsort implements Comparator<Node>{
        @Override
        public int compare(Node t1, Node t2) {
            if (t1.code.compareTo(t2.code) > 0)
                return 1;
            else
                return -1;
        }
    }


    public static void main(String[] args)  {
        String[] fileContents = {"14",
                "D0-POS",
                "AAPL 100",
                "GOOG 200",
                "Cash 10",
                "",
                "D1-TRN",
                "AAPL SL 50 30000",
                "GOOG BY 10 10000",
                "",
                "D1-POS",
                "AAPL 50",
                "GOOG 220",
                "Cash 20000",
                "MSFT 10"};

        reconcile(fileContents);
    }
}

