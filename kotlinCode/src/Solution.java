import java.util.*;
import java.util.function.Consumer;

public class Solution {

    // s = "3[a]2[bc]", return "aaabcbc".
    // s = "3[a2[c]]", return "accaccacc".
    // s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

    // Input:
    // image = [[1,1,1],[1,1,0],[1,0,1]]
    // sr = 1, sc = 1, newColor = 2
    // Output: [[2,2,2],[2,2,0],[2,0,1]]

    public static void main(String[] args) {
        // int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        // int[][] image = {{0,0,0},{0,1,1}};
        // [1,3],[3,0,1],[2],[0]]
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> roomKeys = new ArrayList<>();
        roomKeys.add(1);
        roomKeys.add(3);
        rooms.add(roomKeys);
        roomKeys = new ArrayList<>();
        roomKeys.add(3);
        roomKeys.add(0);
        roomKeys.add(1);
        rooms.add(roomKeys);
        roomKeys = new ArrayList<>();
        roomKeys.add(2);
        rooms.add(roomKeys);
        roomKeys = new ArrayList<>();
        roomKeys.add(0);
        rooms.add(roomKeys);
        System.out.print(new Solution().canVisitAllRooms(rooms));
    }

    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        StringBuilder current = new StringBuilder();
        Stack<String> dataStack = new Stack<>();
        String data;
        String subString;
        int times;
        StringBuilder timesBuilder = new StringBuilder();
        int number;
        for (char c : s.toCharArray()) {
            if (']' == c) {
                data = dataStack.pop();
                while (!"[".equals(data)) {
                    current.insert(0, data);
                    data = dataStack.pop();
                }
                subString = current.toString();
                current.setLength(0);
                timesBuilder.setLength(0);
                number = parseInt(dataStack.peek());
                while (number >= 0) {
                    dataStack.pop();
                    timesBuilder.insert(0, number);
                    if (dataStack.isEmpty()) {
                        break;
                    }
                    number = parseInt(dataStack.peek());
                }
                if (timesBuilder.length() > 0) {
                    times = Integer.parseInt(timesBuilder.toString());
                    while (times-- > 0) {
                        current.append(subString);
                    }
                }
                dataStack.push(current.toString());
                current.setLength(0);
            } else {
                dataStack.push(String.valueOf(c));
            }
        }
        while (!dataStack.isEmpty()) {
            current.insert(0, dataStack.pop());
        }
        result.append(current.toString());
        return result.toString();
    }

    private int parseInt(String aString) {
        try {
            return Integer.parseInt(aString);
        } catch (NumberFormatException exception) {
            // exception.printStackTrace();
            return -1;
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image != null) {
            int rowCount = image.length;
            if (rowCount > 0) {
                int columnCount = image[0].length;
                if (columnCount > 0) {
                    int target = image[sr][sc];
                    boolean reverseColor = false;
                    if (newColor == target) {
                        newColor = -newColor;
                        reverseColor = true;
                    }
                    Queue<Integer> fillQueue = new LinkedList<>();
                    fillQueue.add(sr);
                    fillQueue.add(sc);
                    int row;
                    int column;
                    while (!fillQueue.isEmpty()) {
                        row = fillQueue.poll();
                        column = fillQueue.poll();
                        image[row][column] = newColor;
                        checkToFill(image, fillQueue, row - 1, column, rowCount, columnCount, target);
                        checkToFill(image, fillQueue, row + 1, column, rowCount, columnCount, target);
                        checkToFill(image, fillQueue, row, column - 1, rowCount, columnCount, target);
                        checkToFill(image, fillQueue, row, column + 1, rowCount, columnCount, target);
                    }
                    if (reverseColor) {
                        for (int i = 0; i < rowCount; i++) {
                            for (int j = 0; j < columnCount; j++) {
                                if (image[i][j] == newColor) {
                                    image[i][j] = -newColor;
                                }
                            }
                        }
                    }
                }
            }
        }
        return image;
    }

    private void checkToFill(int[][] image, Queue<Integer> fillQueue, int rowIndex, int columnIndex, int rowCount, int columnCount, int targetColor) {
        if (rowIndex >= 0 && rowIndex < rowCount && columnIndex >= 0 && columnIndex < columnCount && image[rowIndex][columnIndex] == targetColor) {
            fillQueue.add(rowIndex);
            fillQueue.add(columnIndex);
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return true;
        }

        int roomSize =  rooms.size();
        Set<Integer> roomSet = new HashSet<>();
        roomSet.add(0);
        Stack<List<Integer>> keyStack = new Stack<>();
        keyStack.push(rooms.get(0));
        List<Integer> keys;
        while (!keyStack.isEmpty()) {
            keys = keyStack.pop();
            for (int key : keys) {
                if (roomSet.add(key)) {
                    if (roomSet.size() == roomSize) {
                        return true;
                    } else {
                        keyStack.push(keys);
                        keyStack.push(rooms.get(key));
                        break;
                    }
                }
            }
        }

        return roomSet.size() == roomSize;
    }
}
