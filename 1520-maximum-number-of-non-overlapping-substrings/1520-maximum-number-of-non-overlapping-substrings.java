class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i != first[s.charAt(i) - 'a']) continue;

            int end = last[s.charAt(i) - 'a'];
            boolean valid = true;

            for (int j = i; j <= end; j++) {
                int c = s.charAt(j) - 'a';

                if (first[c] < i) {
                    valid = false;
                    break;
                }

                end = Math.max(end, last[c]);
            }

            if (valid) {
                intervals.add(new int[]{i, end});
            }
        }

        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();
        int prevEnd = -1;

        for (int[] in : intervals) {
            if (in[0] > prevEnd) {
                ans.add(s.substring(in[0], in[1] + 1));
                prevEnd = in[1];
            }
        }

        return ans;
    }
}