import java.util.*;

public class StackSolution {

    public static void main(String[] args) {
        // new StackSolution().isValid("()");
        // For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
        // System.out.print(Arrays.toString(new StackSolution().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        // System.out.print(new StackSolution().evalRPN(new String[] {"4", "13", "5", "/", "+"}));
        System.out.print(new StackSolution().findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3));
    }

    //'(', ')', '{', '}', '[' and ']'
    public boolean isValid(String s) {
        if (s == null) {
            return true;
        }
        int length = s.length();
         if (length == 0) {
            return true;
        } else if (length % 2 > 0) {
            return false;
        }

        Map<Character, Character> right = new HashMap<>();
        right.put(')', '(');
        right.put('}', '{');
        right.put(']', '[');

        Stack<Character> characterStack = new Stack<>();
        char last;
        char left;
        for (char c : s.toCharArray()) {
            if (right.containsKey(c)) {
                left = right.get(c);
            } else {
                left = 0;
            }
            if (characterStack.isEmpty()) {
                if (left != 0) {
                    // It is Right, which must not be the first element.
                    return false;
                } else {
                    characterStack.push(c);
                    continue;
                }
            }
            last = characterStack.peek();
            if (left != 0) {
                // It is Right.
                if (left == last) {
                    characterStack.pop();
                } else {
                    return false;
                }
            } else {
                // Add Left ot stack.
                characterStack.add(c);
            }
        }
        return characterStack.isEmpty();
    }

    public int[] dailyTemperatures(final int[] T) {
        if (T == null) {
            return null;
        }
        final int length = T.length;
        if (length == 0) {
            return null;
        }
        int[] result = new int[length];
        int j;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = 0;
            j = i + 1;
            while (j < length) {
                if (T[j] > T[i]) {
                    result[i] += 1;
                    break;
                } else if (0 == result[j]) {
                    result[i] = 0;
                    break;
                } else {
                    result[i] += result[j];
                    j += result[j];
                }
            }
        }
        return result;
    }

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int operand1;
        int operand2;
        for (String token : tokens) {
            if ("+".equals(token)) {
                operand2 = Integer.parseInt(stack.pop());
                operand1 = Integer.parseInt(stack.pop());
                token = "" + (operand1 + operand2);
            } else if ("-".equals(token)) {
                operand2 = Integer.parseInt(stack.pop());
                operand1 = Integer.parseInt(stack.pop());
                token = "" + (operand1 - operand2);
            }else if ("*".equals(token)) {
                operand2 = Integer.parseInt(stack.pop());
                operand1 = Integer.parseInt(stack.pop());
                token = "" + (operand1 * operand2);
            }else if ("/".equals(token)) {
                operand2 = Integer.parseInt(stack.pop());
                operand1 = Integer.parseInt(stack.pop());
                token = "" + (operand1 / operand2);
            }
            stack.push(token);
        }
        return Integer.parseInt(stack.pop());
    }

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null) {
            return 0;
        }
        return findTargetSumWays(nums, nums.length, 0, S);
    }

    private int findTargetSumWays(int[] nums, int length, int index, int S) {
        int method = 0;
        int count = length - index;
        if(0 == count && 0 == S) {
            method++;
        } else if(count > 0) {
            int current = nums[index];
            method += findTargetSumWays(nums, length, index + 1, S - current) + findTargetSumWays(nums, length, index + 1, S + current);
        }
        return method;
    }
}
