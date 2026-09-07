class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;

        long dp = 1; 
        long[] last = new long[26];

        for (char ch : s.toCharArray()) {
            long oldDp = dp;

            dp = (2 * dp - last[ch - 'a'] + MOD) % MOD;

            last[ch - 'a'] = oldDp;
        }

        return (int)((dp - 1 + MOD) % MOD); 
    }
}