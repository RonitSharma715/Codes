class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        for (int rowShift = -n + 1; rowShift < n; rowShift++) {
            for (int colShift = -n + 1; colShift < n; colShift++) {
                maxOverlap = Math.max(maxOverlap,
                        countOverlap(img1, img2, rowShift, colShift));
            }
        }

        return maxOverlap;
    }

    private int countOverlap(int[][] img1, int[][] img2,
                             int rowShift, int colShift) {
        int n = img1.length;
        int overlap = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int nr = r + rowShift;
                int nc = c + colShift;

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (img1[r][c] == 1 && img2[nr][nc] == 1) {
                        overlap++;
                    }
                }
            }
        }

        return overlap;
    }
}