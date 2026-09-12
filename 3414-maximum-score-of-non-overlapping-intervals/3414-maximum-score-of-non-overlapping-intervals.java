import java.util.*;

class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        long[][] arr = new long[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // start
            arr[i][1] = intervals.get(i).get(1); // end
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            long end = arr[i][1];

            int l = i + 1, r = n;
            while (l < r) {
                int m = (l + r) / 2;

                if (arr[m][0] > end) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }

            next[i] = l;
        }

        Node[][] dp = new Node[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Node(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new Node(0, new ArrayList<>());

            for (int k = 1; k <= 4; k++) {

                Node skip = dp[i + 1][k];

                List<Integer> takeList =
                        new ArrayList<>(dp[next[i]][k - 1].indices);

                takeList.add((int) arr[i][3]);
                Collections.sort(takeList);

                Node take = new Node(
                        arr[i][2] + dp[next[i]][k - 1].weight,
                        takeList
                );

                if (take.weight > skip.weight) {
                    dp[i][k] = take;
                } else if (take.weight < skip.weight) {
                    dp[i][k] = skip;
                } else {
                    if (lexicographicallySmaller(
                            take.indices,
                            skip.indices)) {
                        dp[i][k] = take;
                    } else {
                        dp[i][k] = skip;
                    }
                }
            }
        }

        List<Integer> ans = dp[0][4].indices;

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }

        return res;
    }

    private boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }

    static class Node {
        long weight;
        List<Integer> indices;

        Node(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }
}