class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        for (int center = 0; center < n; center++) {

            for (int l = center, r = center; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                if (r - l + 1 >= k) {
                    dp[r + 1] = Math.max(dp[r + 1], dp[l] + 1);
                }
            }
            for (int l = center, r = center + 1; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                if (r - l + 1 >= k) {
                    dp[r + 1] = Math.max(dp[r + 1], dp[l] + 1);
                }
            }

            dp[center + 1] = Math.max(dp[center + 1], dp[center]);
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        return dp[n];
    }
}