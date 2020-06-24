package algorithms;

import java.util.*; 

class DecodeString {

    public static String decode(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ']') {
                stack.push(s.charAt(i));
            } else {
                StringBuilder repeatChar = new StringBuilder();
                StringBuilder repeatCount = new StringBuilder(); 
                boolean isSeen = false; 
                while (!stack.isEmpty()) {
                    if (isSeen && (stack.isEmpty() || !Character.isDigit(stack.peek()))) {
                        break; 
                    }
                    char ch = stack.pop();
                    if (ch == '[') isSeen = true; 
                    else if (Character.isDigit(ch)) repeatCount.insert(0, ch);
                    else repeatChar.insert(0, ch);
                }

                String result = expand(Integer.valueOf(repeatCount.toString()), repeatChar.toString());
                for (char cha2: result.toCharArray()) {
                    stack.push(cha2);
                }
            }
        }
        
        while (!stack.isEmpty()) {
            output.insert(0, stack.pop());
        }

        return output.toString();
    }

    private static String expand(int times, String str) {
        StringBuilder output = new StringBuilder(str.length() * times);
        while (times > 0) {
            output.append(str);
            times--; 
        }

        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(decode("3[a2[c]]"));
    }
}

