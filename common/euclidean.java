/**
* This is a great algorithm in fidning the  greatest comon divisor of two numbers. 
 */
 package algorithms;

public class Euclidean {

    public static int gcd(int a, int b) {

        if (a == b) return a;

        int max = Math.max(a, b);
        int min = Math.min(a, b);

        if (min == 0) return max;

        int temp = min;

        min = max % min;
        max = temp;

        return gcd(min, max);
    }

    public static void main(String[] args) {
        System.out.println(gcd(7, 17));
        System.out.println(gcd(7, 17));
        System.out.println(gcd(100, 46));
        System.out.println(gcd(46, 100));
    }
}