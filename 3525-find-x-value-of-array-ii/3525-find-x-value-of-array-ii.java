class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            prod = 1;
            cnt = new int[k];
        }
    }

    class SegmentTree {
        int n, k;
        Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            tree = new Node[4 * n];
            build(1, 0, n - 1, nums);
        }

        private Node merge(Node left, Node right) {
            Node res = new Node(k);

            res.prod = (left.prod * right.prod) % k;

            for (int r = 0; r < k; r++) {
                res.cnt[r] += left.cnt[r];
            }

            for (int r = 0; r < k; r++) {
                res.cnt[(left.prod * r) % k] += right.cnt[r];
            }

            return res;
        }

        private void build(int idx, int l, int r, int[] nums) {
            if (l == r) {
                tree[idx] = new Node(k);
                int val = nums[l] % k;
                tree[idx].prod = val;
                tree[idx].cnt[val] = 1;
                return;
            }

            int mid = (l + r) >> 1;
            build(idx * 2, l, mid, nums);
            build(idx * 2 + 1, mid + 1, r, nums);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        void update(int pos, int val) {
            update(1, 0, n - 1, pos, val % k);
        }

        private void update(int idx, int l, int r, int pos, int val) {
            if (l == r) {
                tree[idx] = new Node(k);
                tree[idx].prod = val;
                tree[idx].cnt[val] = 1;
                return;
            }

            int mid = (l + r) >> 1;

            if (pos <= mid) {
                update(idx * 2, l, mid, pos, val);
            } else {
                update(idx * 2 + 1, mid + 1, r, pos, val);
            }

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        Node query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        private Node query(int idx, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) {
                return tree[idx];
            }

            int mid = (l + r) >> 1;

            if (qr <= mid) {
                return query(idx * 2, l, mid, ql, qr);
            }

            if (ql > mid) {
                return query(idx * 2 + 1, mid + 1, r, ql, qr);
            }

            Node left = query(idx * 2, l, mid, ql, qr);
            Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);

            return merge(left, right);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        SegmentTree seg = new SegmentTree(nums, k);

        int n = nums.length;
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            seg.update(index, value);

            Node res = seg.query(start, n - 1);
            ans[i] = res.cnt[x];
        }

        return ans;
    }
}