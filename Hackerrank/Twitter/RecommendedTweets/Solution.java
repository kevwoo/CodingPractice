import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    /*
     * Complete the function below.
     */
    static int[] getRecommendedTweets(int[][] followGraph_edges, int[][] likeGraph_edges, int targetUser, int minLikeThreshold) {
        HashSet<Integer> likeTable = new HashSet<>();
        Hashtable<Integer, Integer> followTable = new Hashtable<>();

        // likeTable (HashSet) will contain targetUser's followers
        for (int i = 0; i < followGraph_edges.length; i++) {
            if (followGraph_edges[i][0] == targetUser) {
                likeTable.add(followGraph_edges[i][1]);
            } else
                continue;
        }

        // recTable will contain twitterID recommended by follower as key, and number of likes as value
        for (int i = 0; i < likeGraph_edges.length; i++) {
            if (likeTable.contains(likeGraph_edges[i][0])) {
                if (!followTable.containsKey(likeGraph_edges[i][1])) {
                    followTable.put(likeGraph_edges[i][1], 1);

                } else if (followTable.containsKey(likeGraph_edges[i][1])) {
                    int count = followTable.get(likeGraph_edges[i][1]) + 1;
                    followTable.replace(likeGraph_edges[i][1], count);

                }
            }
        }

        Set<Integer> twitSet = followTable.keySet();
        Comparator<TweetNode> comparator = new PQsort();
        PriorityQueue<TweetNode> q = new PriorityQueue<>(10, comparator);
        int value;
        for (int twitId : twitSet){
            value = followTable.get(twitId);
            if (value >= minLikeThreshold) {
                q.add(new TweetNode(twitId, value));
            }
        }

        int[] solutionArray = new int[q.size()];
        int j = 0;
        while(!q.isEmpty()){
            solutionArray[j] = q.poll().tweetID;
            j++;
        }

        return solutionArray;
    }

    static class TweetNode{
        int tweetID;
        int count;
        TweetNode(int tweetID, int count){
            this.tweetID = tweetID;
            this.count = count;
        }
    }

    static class PQsort implements Comparator<TweetNode>{
        @Override
        public int compare(TweetNode t1, TweetNode t2){
            if (t1.count == t2.count){
                if (t1.tweetID > t2.tweetID)
                    return 1;
                else
                    return -1;
            }
            else if (t1.count > t2.count)
                return -1;
            else
                return 1;

        }
    }


    public static void main(String[] args) throws IOException{
        int[][] followGraph_edges = {{4,3}, {1,2}, {1,3}, {1,4}, {1,5}, {5,6}};
        int[][] likeGraph_edges = {{2,10}, {3,10}, {4,10},{5,10}, {2,11}, {3,12}, {4,11}, {3, 11}, {5, 11}, {4,4}, {2,4}, {3,4}};

        int[] result = getRecommendedTweets(followGraph_edges, likeGraph_edges, 1,3);
        for (int i=0; i<result.length; i++){
            System.out.print(result[i] + " ");
        }
    }
}

