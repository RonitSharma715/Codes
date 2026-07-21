class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";

        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        List<Character> chars = new ArrayList<>();
        List<Integer> lens = new ArrayList<>();

        for (int i = 0; i < t.length();) {
            int j = i;
            while (j < t.length() && t.charAt(j) == t.charAt(i)) {
                j++;
            }

            chars.add(t.charAt(i));
            lens.add(j - i);
            i = j;
        }

        int ans = ones;

        for (int i = 1; i < chars.size() - 1; i++) {
            if (chars.get(i) == '1' &&
                chars.get(i - 1) == '0' &&
                chars.get(i + 1) == '0') {

                ans = Math.max(ans,
                               ones + lens.get(i - 1) + lens.get(i + 1));
            }
        }

        return ans;
    }
}