class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        String mid = "";

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = String.valueOf((char) ('a' + i));
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = s.length() / 2;
        int[] halfCnt = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        StringBuilder prefix = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            boolean found = false;

            for (int ch = 0; ch < 26; ch++) {

                if (halfCnt[ch] == 0) {
                    continue;
                }

                halfCnt[ch]--;
                prefix.append((char) ('a' + ch));

                if (canMakeGreater(prefix, halfCnt, mid, target)) {
                    found = true;
                    break;
                }

                prefix.deleteCharAt(prefix.length() - 1);
                halfCnt[ch]++;
            }

            if (!found) {
                return "";
            }
        }

        String left = prefix.toString();

        return buildPalindrome(left, mid).compareTo(target) > 0
                ? buildPalindrome(left, mid)
                : "";
    }

    private boolean canMakeGreater(
            StringBuilder prefix,
            int[] halfCnt,
            String mid,
            String target) {

        StringBuilder maxLeft = new StringBuilder(prefix);

        for (int i = 25; i >= 0; i--) {
            for (int k = 0; k < halfCnt[i]; k++) {
                maxLeft.append((char) ('a' + i));
            }
        }

        String maxPalindrome = buildPalindrome(maxLeft.toString(), mid);

        return maxPalindrome.compareTo(target) > 0;
    }

    private String buildPalindrome(String left, String mid) {
        return left
                + mid
                + new StringBuilder(left).reverse().toString();
    }
}