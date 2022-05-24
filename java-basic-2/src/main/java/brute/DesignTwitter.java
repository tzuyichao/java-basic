package brute;

import java.util.*;
import java.util.stream.Collectors;

class Twitter {
    private int time;
    private Map<Integer, List<Integer>> follow;
    private Map<Integer, TreeMap<Integer, Integer>> tweets;

    public Twitter() {
        time = 0;
        follow = new HashMap<>();
        tweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        if(!tweets.containsKey(userId)) {
            TreeMap<Integer, Integer> t = new TreeMap<>();
            t.put(time++, tweetId);
            tweets.put(userId, t);
        } else {
            tweets.get(userId).put(time++, tweetId);
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());

        if(tweets.containsKey(userId)) {
            tweets.get(userId).keySet().stream().forEach(key -> {
                map.put(key, tweets.get(userId).get(key));
            });
        }
        if(follow.containsKey(userId)) {
            for(Integer id: follow.get(userId)) {
                if(tweets.containsKey(id)) {
                    tweets.get(id).keySet().stream().forEach(key -> {
                        map.put(key, tweets.get(id).get(key));
                    });
                }
            }
        }
        return map.values().stream().limit(10).collect(Collectors.toList());
    }

    public void follow(int followerId, int followeeId) {
        if(follow.containsKey(followerId)) {
            follow.get(followerId).add(followeeId);
        } else {
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.add(followeeId);
            follow.put(followerId, linkedList);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if(followeeId != followerId && follow.get(followerId) != null && follow.get(followerId).contains(followeeId)) {
            follow.get(followerId).remove(follow.get(followerId).indexOf(followeeId));
        }
    }
}

/**
 * 355. Design Twitter
 * https://leetcode.com/problems/design-twitter/
 */
public class DesignTwitter {
    public static void main(String[] args) {
        var userId1 = 1;
        var tweetId1 = 5;
        var followerId = 1;
        var followeeId = 2;
        var userId2 = followeeId;
        var tweetId2 = 6;

        Twitter obj = new Twitter();
        obj.postTweet(userId1,tweetId1);
        List<Integer> param_2 = obj.getNewsFeed(userId1);
        System.out.println(param_2);
        obj.follow(followerId,followeeId);
        obj.postTweet(userId2, tweetId2);
        List<Integer> param_3 = obj.getNewsFeed(userId1);
        System.out.println(param_3);
        obj.unfollow(followerId,followeeId);
        List<Integer> param_4 = obj.getNewsFeed(userId1);
        System.out.println(param_4);
    }
}
