package com.roberto.concurrency.pb2;

import java.util.concurrent.SynchronousQueue;

import static com.roberto.concurrency.pb2.Util.GCD;
import static java.lang.String.format;

public class Numitor implements Runnable {

    private final SynchronousQueue<Integer> synchronousQueue;
    private final int n;
    private final int k;

    public Numitor(SynchronousQueue<Integer> synchronousQueue, int n, int k) {
        this.synchronousQueue = synchronousQueue;
        this.n = n;
        this.k = k;
    }

    @Override
    public void run() {
        long produsIntreg = 1L;  //produsul tuturor perechilor "numarator/numitor" care se impart exact, la fiecare pas.
        long produsNumitor = 1L;
        long produsNumarator = 1L;
        try {
            for (int i = 1; i <= k; i++) {

                int numarator = synchronousQueue.take();

                if (numarator % i == 0) {
                    produsIntreg *= (numarator / i);
                } else {
                    produsNumitor *= i;
                    produsNumarator *= numarator;
                }
            }

            long gcd = GCD(produsIntreg * produsNumarator, produsNumitor);
            System.out.println(format("Combinari de n luate cate k : %d", produsIntreg * produsNumarator / gcd));

        } catch (InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
