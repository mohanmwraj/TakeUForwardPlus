/*
    * Given a string text and a string pattern,
    * implement the Rabin-Karp algorithm to find the starting index of
    * all occurrences of pattern in text.
    * If pattern is not found, return an empty list.
    *
    * Input: text = "ababcabcababc", pattern = "abc"
    * Output: [2, 5, 10]
 */

import java.util.ArrayList;
import java.util.List;

public class robinKarpAlgorithm {
    /*
        * Approach: Brute Force
        *
        * Use two nested loops
        *
     */
    public List<Integer> search(String pat, String txt) {
        int n = pat.length();
        int m = txt.length();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= m - n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    flag = false;
                    break;
                }
            }

            if (flag) ans.add(i);
        }

        return ans;
    }
    /*
        * Time Complexity: O(M * N)
        * Space Complexity: O(k)
     */

    /*
        * Approach: Robin Karp Algorithm
        *
        *
     */
    public List<Integer> search(String pat, String txt) {
        long hashPat = 0;
        long hashWindow = 0;
        long pr = 7L;
        long mod = 577L;
        int patLen = pat.length();
        int textLen = txt.length();

        if(patLen > textLen) return new ArrayList<>();

        long prPow = 1;
        for(int i = 0; i < patLen; ++i){
            hashPat = (hashPat + ((pat.charAt(i) - 'a' + 1) * prPow) % mod) % mod;
            hashWindow = (hashWindow + ((txt.charAt(i) - 'a' + 1) * prPow) % mod) % mod;
            prPow = (pr * prPow) % mod;
        }

        List<Integer> result = new ArrayList<>();
        int leftPrPow = 1;

        for(int i = 0; i <= textLen - patLen; ++i){
            if(hashPat == hashWindow){
                if(txt.substring(i, patLen) == pat){
                    result.add(i);
                }
            }

            hashWindow = (hashWindow - ((txt.charAt(i) - 'a' + 1) * leftPrPow) % mod + mod) % mod;
            hashWindow = (hashWindow + ((txt.charAt(i + patLen) - 'a' + 1) * prPow) % mod) % mod;

            hashPat = (hashPat * pr) % mod;
            prPow = (pr * prPow) % mod;
            leftPrPow = (int) ((pr * leftPrPow) % mod);
        }

        return result;
    }
}
