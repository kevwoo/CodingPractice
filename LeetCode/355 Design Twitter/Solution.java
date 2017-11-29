import java.util.*;

class Twitter {
    LinkedList<Integer> feedList;
    LinkedList<Node> postList;
    HashMap<Integer, User> userMap;
    /** Initialize your data structure here. */

    public Twitter() {
        userMap = new HashMap<>();
        feedList = new LinkedList<>();
        postList = new LinkedList<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId))
            userMap.put(userId, new User(userId));

        postList.addFirst(new Node(userId, tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        feedList.clear();
        User sample = userMap.get(userId);
        if (sample == null)
            return feedList;
        int counter = 0;
        for (Node tweets : postList){
            if (sample.followMap.contains(tweets.userId)) {
                feedList.add(tweets.tweetId);
                counter++;
            }
            if (counter == 10)
                break;
        }
        return feedList;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId))
            userMap.put(followerId, new User(followerId));
        User sample = userMap.get(followerId);
        sample.addFollow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User sample = userMap.get(followerId);
        if (sample == null || followerId == followeeId)
            return;
        sample.unFollow(followeeId);
    }

    static class Node{
        int userId;
        int tweetId;

        Node(int user, int tweet){
            userId = user;
            tweetId = tweet;
        }
    }

    static class User{
        int userId;
        HashSet<Integer> followMap;

        User(int userId){
            this.userId = userId;
            followMap = new HashSet<>();
            followMap.add(userId);
        }

        public void addFollow(int followId){
            if (!followMap.contains(followId))
                followMap.add(followId);
        }

        public void unFollow(int followId){
            if (followMap.contains(followId))
                followMap.remove(followId);
        }

    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
