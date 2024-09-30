import java.util.ArrayDeque;
import java.util.Deque;

// LC 733
public class FloodFill {

    final int[] dirs = {0, 1, 0, -1, 0};

    /**
     * Traverse the connected nodes starting from the source node.
     * Apply BFS or DFS.
     * <p>
     * TC: O(m*n)
     * SC: O(m*n)
     *
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int sourceColor = image[sr][sc];
//        dfs(image, sr, sc, color, sourceColor);
        bfs(image, sr, sc, color, sourceColor);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int color, int sourceColor) {
        if (image[sr][sc] == color) {
            // stop if matching color
            return;
        }
        if (image[sr][sc] != sourceColor) {
            // don't color is source color not found
            return;
        }
        // color it == mark as visited
        image[sr][sc] = color;
        int m = image.length;
        int n = image[0].length;
        // adj. list
        for (int delta = 1; delta < dirs.length; delta++) {
            int row = sr + dirs[delta - 1];
            int col = sc + dirs[delta];
            // bounds check
            if (row < 0 || row >= m || col < 0 || col >= n) {
                continue;
            }
            dfs(image, row, col, color, sourceColor);
        }
    }

    private void bfs(int[][] image, int sr, int sc, int color, int sourceColor) {
        if (image[sr][sc] == color) {
            // stop if matching color
            return;
        }

        int m = image.length;
        int n = image[0].length;
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(sr, sc));
        // color it == mark as visited
        image[sr][sc] = color;

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            // adj. list
            for (int delta = 1; delta < dirs.length; delta++) {
                int row = curr.row + dirs[delta - 1];
                int col = curr.col + dirs[delta];
                // bounds check
                if (row < 0 || row >= m || col < 0 || col >= n) {
                    continue;
                }
                if (image[row][col] == color || image[row][col] != sourceColor) {
                    // stop if matching color
                    // don't color is source color not found
                    continue;
                }
                image[row][col] = color;
                queue.offer(new Pair(row, col));
            }
        }
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
