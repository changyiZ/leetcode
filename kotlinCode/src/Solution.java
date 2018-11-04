import java.util.Stack;

public class Solution {

    // s = "3[a]2[bc]", return "aaabcbc".
    // s = "3[a2[c]]", return "accaccacc".
    // s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

    public static void main(String[] args) {
        System.out.print(new Solution().decodeString("3[a]2[bc]"));
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
}
