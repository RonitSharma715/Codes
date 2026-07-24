class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Deduplicate numbers
        HashSet<Integer> set = new HashSet<>();
        int maxVal = 0;
        for (int x : nums) {
            set.add(x);
            maxVal = Math.max(maxVal, x);
        }

        int limit = 1;
        while (limit <= maxVal) {
            limit <<= 1;
        }

        boolean[] pairXor = new boolean[limit];
        boolean[] ans = new boolean[limit];

        Integer[] unique = set.toArray(new Integer[0]);

        // All XORs of two numbers
        for (int x : unique) {
            for (int y : unique) {
                pairXor[x ^ y] = true;
            }
        }

        // XOR with the third number
        for (int v = 0; v < limit; v++) {
            if (!pairXor[v]) continue;
            for (int z : unique) {
                ans[v ^ z] = true;
            }
        }

        int res = 0;
        for (boolean b : ans) {
            if (b) res++;
        }

        return res;
    }
}