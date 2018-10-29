public class QueueSolution {

    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int rowCount = grid.length;
        if (0 == rowCount) {
            return 0;
        }
        int columnCount = grid[0].length;

        int count = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j< columnCount; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    markLands(grid, i, j, rowCount, columnCount);
                }
            }
        }
        return count;
    }

    private void markLands(char[][] grid, int i, int j, int rowCount, int columnCount) {
        if (i< 0 || j < 0 || i >= rowCount || j >= columnCount || '1' != grid[i][j]) {
            return;
        }
        grid[i][j] = 'x';
        markLands(grid, i - 1, j, rowCount, columnCount);
        markLands(grid, i + 1, j, rowCount, columnCount);
        markLands(grid, i, j - 1, rowCount, columnCount);
        markLands(grid, i, j + 1, rowCount, columnCount);
    }
}
