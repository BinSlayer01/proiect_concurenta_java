package com.roberto.concurrency.pb1;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class Reader implements Runnable {

    private final static List<Book> AVAILABLE_BOOKS;

    static {
        AVAILABLE_BOOKS = List.of(
            new Book("Java for Dummies"),
            new Book("Learn C++ from the start"),
            new Book("Star Wars Episode IV"),
            new Book("Baltagul"),
            new Book("Cum sa slabesti in 10 zile")
        );
    }

    private String name;

    public Reader(String name) {
        this.name = name;
    }

    public void readBookAndReturnToShelf(int i) throws InterruptedException {
        Book book = AVAILABLE_BOOKS.get(i);
        System.out.println(format("%s vrea sa ia cartea cu titlul '%s'\n", this.name, book.getTitle()));

        synchronized (book) {

            long readingTime = ThreadLocalRandom.current().nextLong(500L, 3000L);
            System.out.println(format("%s a luat cartea cu titlul '%s' si o citeste timp de %d milisecunde\n",
                this.name, book.getTitle(), readingTime));

            Thread.sleep(readingTime);

            System.out.println(format("%s a pus cartea cu titlul '%s' inapoi pe raft\n",
                this.name, book.getTitle()));
        }
    }

    @Override
    public void run() {
        int randomNumberUpToResourcesLimit = ThreadLocalRandom.current().nextInt(1, AVAILABLE_BOOKS.size());
        System.out.println(this.name + " a intrat in Biblioteca si a decis sa imprumute "
            + randomNumberUpToResourcesLimit + " carti"
        );

        try {
            for (int i = 0; i < randomNumberUpToResourcesLimit; i++) {
                readBookAndReturnToShelf(ThreadLocalRandom.current().nextInt(0, AVAILABLE_BOOKS.size() - 1));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
