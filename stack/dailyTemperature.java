package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
/*
    Input: temperatures = [30,38,30,36,35,40,28]
    Output: [1,4,1,2,1,0,0]
 */
public class dailyTemperature {
    /*
        * Approach: Brute Force
        *
     */
    public int[] dailyTemperatures_1(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int count = 1;
            int j = i + 1;
            while (j < n) {
                if (temperatures[j] > temperatures[i]) {
                    break;
                }
                j++;
                count++;
            }
            count = (j == n) ? 0 : count;
            res[i] = count;
        }
        return res;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    /*
        * Approach: Stack
        *
     */
    public int[] dailyTemperatures_2(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>(); // pair: [temp, index]

        for (int i = 0; i < temperatures.length; i++) {
            int t = temperatures[i];
            while (!stack.isEmpty() && t > stack.peek()[0]) {
                int[] pair = stack.pop();
                res[pair[1]] = i - pair[1];
            }
            stack.push(new int[]{t, i});
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    /*
        * Approach: Dynamic Programming
        *
     */
    public int[] dailyTemperatures_3(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n && temperatures[j] <= temperatures[i]) {
                if (res[j] == 0) {
                    j = n;
                    break;
                }
                j += res[j];
            }

            if (j < n) {
                res[i] = j - i;
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */

    /*
        * Approach: Monotonic Increasing Stack
        *
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque();

        for(int currDay = 0; currDay < n; currDay++){
            int currentTemp = temperatures[currDay];

            while(!stack.isEmpty() && temperatures[stack.peek()] < currentTemp){
                int prevDay = stack.pop();
                answer[prevDay] = currDay - prevDay;
            }
            stack.push(currDay);
        }

        return answer;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */
}
