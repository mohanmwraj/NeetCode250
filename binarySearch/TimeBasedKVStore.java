package binarySearch;

import java.util.*;

public class TimeBasedKVStore {
    /*
        * Approach: Brute Force
        *
     */
    public class TimeMap_1 {
        private Map<String, Map<Integer, List<String>>> keyStore;

        public TimeMap_1() {
            keyStore = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!keyStore.containsKey(key)) {
                keyStore.put(key, new HashMap<>());
            }
            if (!keyStore.get(key).containsKey(timestamp)) {
                keyStore.get(key).put(timestamp, new ArrayList<>());
            }
            keyStore.get(key).get(timestamp).add(value);
        }

        public String get(String key, int timestamp) {
            if (!keyStore.containsKey(key)) {
                return "";
            }
            int seen = 0;

            for (int time : keyStore.get(key).keySet()) {
                if (time <= timestamp) {
                    seen = Math.max(seen, time);
                }
            }
            if (seen == 0) return "";
            int back = keyStore.get(key).get(seen).size() - 1;
            return keyStore.get(key).get(seen).get(back);
        }
    }
    /*
        Time Complexity: O(1) for set() and O(n) for get().
        Space Complexity: O(m * n)
     */
    /*
        * Approach: Binary Search Sorted Map
        *
     */
    public class TimeMap_2 {
        private Map<String, TreeMap<Integer, String>> m;

        public TimeMap_2() {
            m = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            m.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!m.containsKey(key)) return "";
            TreeMap<Integer, String> timestamps = m.get(key);
            Map.Entry<Integer, String> entry = timestamps.floorEntry(timestamp);
            return entry == null ? "" : entry.getValue();
        }
    }
    /*
        Time Complexity: O(1) for set() and O(log n) for get().
        Space Complexity: O(m * n)
     */
    /*
        * Approach: Binary Search Array
        *
     */
    public class TimeMap_3 {

        private Map<String, List<Pair<Integer, String>>> keyStore;

        public TimeMap_3() {
            keyStore = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            keyStore.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair<>(timestamp, value));
        }

        public String get(String key, int timestamp) {
            List<Pair<Integer, String>> values = keyStore.getOrDefault(key, new ArrayList<>());
            int left = 0, right = values.size() - 1;
            String result = "";

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (values.get(mid).getKey() <= timestamp) {
                    result = values.get(mid).getValue();
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return result;
        }

        private static class Pair<K, V> {
            private final K key;
            private final V value;

            public Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }
        }
    }
    /*
        Time Complexity: O(1) for set() and O(log n) for get().
        Space Complexity: O(m * n)
     */
}
