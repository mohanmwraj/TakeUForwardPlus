public class fibonacci {
    /*
        * Recurrence Relation
        *
        f(n){
            if(n <= 1) return n

            return f(n - 1) + f(n - 2)
        }
     */
    /*
        * Approach: Recursion + Memoization
        *
     */
    public static int f_memo(int n, int[] dp){
        if(n<=1) return n;

        if(dp[n]!= -1) return dp[n];
        return dp[n]= f_memo(n-1,dp) + f_memo(n-2,dp);
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Tabulation
        *
     */

    public static int tabulation(int n){
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; ++i){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(n)
     */

    /*
        * Approach: Space Optimization
        *
        * Step 1 : prev2 prev   curr
        * Step 2 :       prev2  prev  curr
     */
    public static int spaceOptimization(int n){
        int prev2 = 0;
        int prev = 1;

        for(int i = 2; i <= n; ++i){
            int curr = prev + prev2;
            prev2 = prev;
            prev = curr;
        }

        return prev;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
