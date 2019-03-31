package com.roberto.concurrency.pb2;

import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

import static java.lang.String.format;

/**
 * Scrieţi un program concurent care să calculeze Cnk folosind două procese concurente care
 * calculează succesiv produsele parţiale ale lui nx(n-1)x...x(n-k+1), respectiv ale lui 1x2x...xk,
 * făcând sincronizarea între ele astfel încât al doilea proces să efectueze şi raportul acestor valori atunci
 * când rezultatul este întreg.
 * Procesul Numarator va transmite procesului Numitor succesiv valorile v=n,n-1,...,n-k+1; procesul Numitor
 * calculează la fiecare pas câtul cnk dintre valoarea primită şi valorile 1,2,...,k.
 */
public class PbCombinationsNK {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Citeste n: ");
        int n = scanner.nextInt();
        System.out.print("Citeste k: ");
        int k = scanner.nextInt();

        if (k <= n && 0<=k) {
            new Thread(new Numarator(synchronousQueue, n, k))
                .start();

            new Thread(new Numitor(synchronousQueue, n, k))
                .start();

        } else {
            System.out.println("Eroare: Valorile trebuie sa fie de forma n >= k >= 0");
        }
    }

}
