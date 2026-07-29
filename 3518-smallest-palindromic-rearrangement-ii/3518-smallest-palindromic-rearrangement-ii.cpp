class Solution {
public:
    const long long LIM = 1000000 + 5;

    long long C(int n, int k) {
        if (k > n) return 0;
        k = min(k, n - k);
        long long ans = 1;
        for (int i = 1; i <= k; i++) {
            ans = ans * (n - i + 1) / i;
            if (ans > LIM) return LIM;
        }
        return ans;
    }

    long long countWays(vector<int>& cnt) {
        int total = 0;
        for (int x : cnt) total += x;

        long long ans = 1;
        int rem = total;

        for (int x : cnt) {
            if (x == 0) continue;
            ans *= C(rem, x);
            if (ans > LIM) return LIM;
            rem -= x;
        }

        return ans;
    }

    string smallestPalindrome(string s, int k) {
        vector<int> freq(26, 0);
        for (char c : s) freq[c - 'a']++;

        vector<int> half(26, 0);
        string mid = "";

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            if (freq[i] % 2)
                mid = string(1, char('a' + i));
        }

        if (countWays(half) < k)
            return "";

        string left;
        int len = s.size() / 2;

        for (int pos = 0; pos < len; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                long long ways = countWays(half);

                if (ways >= k) {
                    left.push_back('a' + c);
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        string right = left;
        reverse(right.begin(), right.end());

        return left + mid + right;
    }
};