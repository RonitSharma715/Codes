import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        int i = 0;
        while (i < n) {
            int j = i + 1;

            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }

            Integer[] positions = Arrays.copyOfRange(idx, i, j);
            Arrays.sort(positions);

            for (int k = i; k < j; k++) {
                ans[positions[k - i]] = nums[idx[k]];
            }

            i = j;
        }

        return ans;
    }
}