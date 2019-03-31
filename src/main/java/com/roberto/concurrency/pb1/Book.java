package com.roberto.concurrency.pb1;

import java.util.Objects;

public class Book {

    private final String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
            "title='" + title + '\'' +
            '}';
    }
}
