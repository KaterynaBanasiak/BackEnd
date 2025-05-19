package de.ait.demo1.model;

public class Film {
    private String title;
    private int year;

    // Конструктор
    public Film(String title, int year) {
        this.title = title;
        this.year = year;
    }

    // Геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
