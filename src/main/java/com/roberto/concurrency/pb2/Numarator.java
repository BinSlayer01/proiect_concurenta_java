package com.roberto.concurrency.pb2;

import java.util.concurrent.SynchronousQueue;

public class Numarator implements Runnable {

    private final SynchronousQueue<Integer> synchronousQueue;
    private final int n;
    private final int k;

    public Numarator(SynchronousQueue<Integer> synchronousQueue, int n, int k) {
        this.synchronousQueue = synchronousQueue;
        this.n = n;
        this.k = k;
    }

    @Override
    public void run() {
        try {
            for (int i = (n - k + 1); i <= n; i++) {
                synchronousQueue.put(i);
            }

        } catch (InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
