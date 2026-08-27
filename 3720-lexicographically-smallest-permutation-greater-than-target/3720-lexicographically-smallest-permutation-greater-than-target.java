class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int i = 0;

        while (i < n && cnt[target.charAt(i) - 'a'] > 0) {
            cnt[target.charAt(i) - 'a']--;
            i++;
        }

        while (true) {
            if (i < n) {
                int cur = target.charAt(i) - 'a';

                for (int c = cur + 1; c < 26; c++) {
                    if (cnt[c] > 0) {
                        StringBuilder ans = new StringBuilder();

                        ans.append(target.substring(0, i));

                        cnt[c]--;
                        ans.append((char) ('a' + c));

                        for (int k = 0; k < 26; k++) {
                            while (cnt[k]-- > 0) {
                                ans.append((char) ('a' + k));
                            }
                        }

                        return ans.toString();
                    }
                }
            }

            if (i == 0) {
                return "";
            }

            i--;
            cnt[target.charAt(i) - 'a']++;
        }
    }
}