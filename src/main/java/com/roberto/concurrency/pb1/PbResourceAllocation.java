package com.roberto.concurrency.pb1;


/**
 * CERINTA: Problema alocării resurselor. Există n obiecte. Mai multe persoane doresc să ia
 * un număr aleator de obiecte, pe care să le folosească şi apoi să le returneze.
 * *
 * Rezolvare folosind blocuri sincronizate.
 * Problema presupune ca o aceeasi resursa (o carte) nu poate fi citita decat de maxim 1 persoana
 * la un moment dat, pana ce nu este returnata pe raft.
 */
public class PbResourceAllocation {

    public static void main(String[] args) {

        new Thread(
            new Reader("Andrei")
        ).start();

        new Thread(
            new Reader("Grigore")
        ).start();

        new Thread(
            new Reader("Vasile")
        ).start();

        new Thread(
            new Reader("Cristian")
        ).start();

        new Thread(
            new Reader("Samuel")
        ).start();

        new Thread(
            new Reader("Daniel")
        ).start();

    }
}
