package de.ait.demo1.model;

public class Book {
    private String title;
    private String author;

    // Конструктор
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
