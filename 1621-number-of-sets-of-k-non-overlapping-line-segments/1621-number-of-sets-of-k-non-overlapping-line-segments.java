class Solution {
    private static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        long ans = 1;

        for (int i = 1; i <= R; i++) {
            ans = ans * (N - R + i) % MOD;
            ans = ans * modPow(i, MOD - 2) % MOD;
        }

        return (int) ans;
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }

        return result;
    }
}