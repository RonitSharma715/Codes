import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litterId = new int[m][n];
        int sx = 0, sy = 0, cnt = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    litterId[i][j] = cnt++;
                }
            }
        }

        if (cnt == 0) return 0;

        int startMask = (1 << cnt) - 1;

        boolean[][][][] vis =
                new boolean[m][n][energy + 1][1 << cnt];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy, energy, startMask});
        vis[sx][sy][energy][startMask] = true;

        int[] dirs = {-1, 0, 1, 0, -1};
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();

                int x = cur[0];
                int y = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if (mask == 0) return moves;

                if (e == 0) continue;

                for (int k = 0; k < 4; k++) {
                    int nx = x + dirs[k];
                    int ny = y + dirs[k + 1];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                        continue;

                    char cell = classroom[nx].charAt(ny);

                    if (cell == 'X')
                        continue;

                    int ne = e - 1;
                    int nMask = mask;

                    if (cell == 'L') {
                        nMask &= ~(1 << litterId[nx][ny]);
                    }

                    if (cell == 'R') {
                        ne = energy;
                    }

                    if (!vis[nx][ny][ne][nMask]) {
                        vis[nx][ny][ne][nMask] = true;
                        q.offer(new int[]{nx, ny, ne, nMask});
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}