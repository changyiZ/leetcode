import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class QueueSolution {

    public static void main(String[] args) {
        QueueSolution solution = new QueueSolution();
        solution.openLock(new String[] {"0201","0101","0102","1212","2002"}, "0202");
    }

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

    public int openLock(String[] deadends, String target) {
        Set<String> begin = new HashSet<>();
        begin.add("0000");
        Set<String> end = new HashSet<>();
        end.add(target);
        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));
        int length = target.length();
        int step = 0;
        char[] charArray;
        char tempChar;
        String temp;
        Set<String> tempSet;
        int i;
        while (!begin.isEmpty() && !end.isEmpty()) {
            tempSet = new HashSet<>();
            for (String s : begin) {
                if (end.contains(s)) {
                    return step;
                }
                if (deadendSet.contains(s)) {
                    continue;
                }
                deadendSet.add(s);
                charArray = s.toCharArray();
                for (i = 0; i < length; i++) {
                    tempChar = charArray[i];
                    charArray[i] = (char) ((tempChar - '0' + 1) % 10 + '0');
                    temp = new String(charArray);
                    if (!deadendSet.contains(temp)) {
                        tempSet.add(temp);
                    }
                    charArray[i] = (char) ((tempChar - '0' + 9) % 10 + '0');
                    temp = new String(charArray);
                    if (!deadendSet.contains(temp)) {
                        tempSet.add(temp);
                    }
                    charArray[i] = tempChar;
                }
            }
            step++;

            if (end.size() < tempSet.size()) {
                begin = end;
                end = tempSet;
            } else {
                begin = tempSet;
            }
        }
        return -1;
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        int i = 0;
        int j;
        int min;
        int current;
        while (++i <= n) {
            j = 1;
            min = n;
            while (j * j <= i) {
                current = dp[i - j * j] + 1;
                if (current < min) {
                    min = current;
                }
                j++;
            }
            dp[i] = min;
        }

        return dp[n];
    }
}
