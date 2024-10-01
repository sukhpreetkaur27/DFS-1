import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// LC 542
public class _01Matrix {

    /**
     * Find distance == apply a traversal algo
     * Nearest Distance == BFS traversal == treating all sources at same level and then radiating outwards
     * <p>
     * Now, to find the distance to the nearest 0 --> if we treat all 1's as sources and then try to find the nearest 0, it will boil down to noodles solution (DFS pattern)
     * <p>
     * If we treat all 0's as source and at distance 0 --> then, radiating in all 4 directions results in finding the min. (nearest distance to 1)
     * by incrementing by 1 the distance to neighbour node from the parent's distance.
     * <p>
     * TC: O(m*n)
     * SC: O(m*n)
     *
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] visited = new int[m][n];
        for (int[] i : visited) {
            Arrays.fill(i, -1);
        }

        Deque<Pair> queue = new ArrayDeque<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 0) {
                    visited[r][c] = 0;
                    queue.offer(new Pair(r, c));
                }
            }
        }

        int[] dirs = {0, 1, 0, -1, 0};
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            // move in all 4 directions
            for (int i = 1; i < dirs.length; i++) {
                int row = curr.row + dirs[i - 1];
                int col = curr.col + dirs[i];

                // bounds check
                if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] != -1) {
                    continue;
                }
                visited[row][col] = visited[curr.row][curr.col] + 1;
                queue.offer(new Pair(row, col));
            }
        }
        return visited;
    }

    private class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
