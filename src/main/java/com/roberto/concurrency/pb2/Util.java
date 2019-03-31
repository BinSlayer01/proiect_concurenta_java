package com.roberto.concurrency.pb2;

public class Util {

    /**
     * Greatest Common Divisor
     */
    public static long GCD(long a, long b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }
}
