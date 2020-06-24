package algorithms; 

import java.util.*; 

class Parenthesis  {

    public static String removeParenthesis(String n) {
        // "() (()()()()())"
        Deque<Pair> stack = new ArrayDeque<>();

        for (int i = 0; i < n.length(); i++) {

            if (n.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek().ch != '(') stack.push(new Pair(i, ')'));
                else stack.pop();
            }

            if (n.charAt(i) == '(') {
                stack.push(new Pair(i, '('));
            }
        }

        StringBuilder rebuildString = new StringBuilder();

        int currentIndex = -1; 
        for (int i = 0; i < n.length(); i++) {
            if (!stack.isEmpty()) {
                currentIndex = stack.removeLast().index;
            }

            if (i == currentIndex) {
                continue;
            }
            rebuildString.append(n.charAt(i));
        }

        return rebuildString.toString();
    }

    public static void main(String[] args) {
        String s = "ab(a(c)fg)9)";
        // (a)b(c)d(e)f)(g) 
        System.out.println(removeParenthesis(s));
    }
}

class Pair {
    int index; 
    char ch;

    public Pair(int index, char ch) {
        this.index = index;
        this.ch = ch;
    }
}
