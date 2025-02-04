package heapPriorityQueue;

import java.util.*;

public class designTwitter {
    /*
        * Approach: Sorting
        *
     */
    public class Twitter_1 {
        private int time;
        private Map<Integer, Set<Integer>> followMap;
        private Map<Integer, List<int[]>> tweetMap;

        public Twitter_1() {
            time = 0;
            followMap = new HashMap<>();
            tweetMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            tweetMap.putIfAbsent(userId, new ArrayList<>());
            tweetMap.get(userId).add(new int[]{time++, tweetId});
        }

        public List<Integer> getNewsFeed(int userId) {
            List<int[]> feed = new ArrayList<>(tweetMap.getOrDefault(userId, new ArrayList<>()));
            for (int followeeId : followMap.getOrDefault(userId, new HashSet<>())) {
                feed.addAll(tweetMap.getOrDefault(followeeId, new ArrayList<>()));
            }
            feed.sort((a, b) -> b[0] - a[0]);
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < Math.min(10, feed.size()); i++) {
                res.add(feed.get(i)[1]);
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            if (followerId != followeeId) {
                followMap.putIfAbsent(followerId, new HashSet<>());
                followMap.get(followerId).add(followeeId);
            }
        }

        public void unfollow(int followerId, int followeeId) {
            followMap.getOrDefault(followerId, new HashSet<>()).remove(followeeId);
        }
    }
    /*
        Time complexity: O(n∗m+t log t) for each getNewsFeed() call and O(1) for remaining methods.
        Space complexity: O(N∗m+N∗M)
     */
    /*
        * Approach: Heap
        *
     */
    public class Twitter_2 {

        private int count;
        private Map<Integer, List<int[]>> tweetMap;
        private Map<Integer, Set<Integer>> followMap;

        public Twitter_2() {
            count = 0;
            tweetMap = new HashMap<>();
            followMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[]{count--, tweetId});
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

            followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
            for (int followeeId : followMap.get(userId)) {
                if (tweetMap.containsKey(followeeId)) {
                    List<int[]> tweets = tweetMap.get(followeeId);
                    int index = tweets.size() - 1;
                    int[] tweet = tweets.get(index);
                    minHeap.offer(new int[]{tweet[0], tweet[1], followeeId, index});
                }
            }

            while (!minHeap.isEmpty() && res.size() < 10) {
                int[] curr = minHeap.poll();
                res.add(curr[1]);
                int index = curr[3];
                if (index > 0) {
                    int[] tweet = tweetMap.get(curr[2]).get(index - 1);
                    minHeap.offer(new int[]{tweet[0], tweet[1], curr[2], index - 1});
                }
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            followMap.computeIfPresent(followerId, (k, v) -> {
                v.remove(followeeId);
                return v;
            });
        }
    }
    /*
        Time complexity: O(n log n) for each getNewsFeed() call and O(1) for remaining methods.
        Space complexity: O(N∗m+N∗M + n)
     */
    /*
        * Approach: Heap Optimal
        *
     */
    public class Twitter_3 {

        private int count;
        private Map<Integer, List<int[]>> tweetMap;
        private Map<Integer, Set<Integer>> followMap;

        public Twitter_3() {
            this.count = 0;
            this.tweetMap = new HashMap<>();
            this.followMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            tweetMap.computeIfAbsent(userId, k -> new ArrayList<>())
                    .add(new int[]{count, tweetId});
            if (tweetMap.get(userId).size() > 10) {
                tweetMap.get(userId).remove(0);
            }
            count--;
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                    (a, b) -> Integer.compare(a[0], b[0])
            );
            followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
            if (followMap.get(userId).size() >= 10) {
                PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                        (a, b) -> Integer.compare(a[0], b[0])
                );
                for (int followeeId : followMap.get(userId)) {
                    if (!tweetMap.containsKey(followeeId)) continue;
                    List<int[]> tweets = tweetMap.get(followeeId);
                    int index = tweets.size() - 1;
                    int[] tweet = tweets.get(index);
                    maxHeap.offer(new int[]{-tweet[0], tweet[1], followeeId, index - 1});
                    if (maxHeap.size() > 10) {
                        maxHeap.poll();
                    }
                }
                while (!maxHeap.isEmpty()) {
                    int[] top = maxHeap.poll();
                    minHeap.offer(new int[]{-top[0], top[1], top[2], top[3]});
                }
            } else {
                for (int followeeId : followMap.get(userId)) {
                    if (!tweetMap.containsKey(followeeId)) continue;
                    List<int[]> tweets = tweetMap.get(followeeId);
                    int index = tweets.size() - 1;
                    int[] tweet = tweets.get(index);
                    minHeap.offer(new int[]{tweet[0], tweet[1], followeeId, index - 1});
                }
            }

            while (!minHeap.isEmpty() && res.size() < 10) {
                int[] top = minHeap.poll();
                res.add(top[1]);
                int nextIndex = top[3];
                if (nextIndex >= 0) {
                    List<int[]> tweets = tweetMap.get(top[2]);
                    int[] nextTweet = tweets.get(nextIndex);
                    minHeap.offer(new int[]{nextTweet[0], nextTweet[1], top[2], nextIndex - 1});
                }
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followMap.containsKey(followerId)) {
                followMap.get(followerId).remove(followeeId);
            }
        }
    }
    /*
        Time complexity: O(n) for each getNewsFeed() call and O(1) for remaining methods.
        Space complexity: O(N∗m+N∗M + n)
     */
}
