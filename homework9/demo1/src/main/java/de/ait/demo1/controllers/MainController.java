package de.ait.demo1.controllers;

import de.ait.demo1.model.Book;
import de.ait.demo1.model.Film;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @GetMapping("/info")
    public String info() {
        // Возвращаем содержимое файла info.html как строку
        // Но удобнее положить info.html в resources/static и обращаться к нему через браузер напрямую
        return "<html><body>" +
                "<h1>Добро пожаловать на сервер Spring Boot!</h1>" +
                "<p>Доступные эндпоинты:</p>" +
                "<ul>" +
                "<li>/books - список книг</li>" +
                "<li>/films - список фильмов</li>" +
                "<li>/now - текущее дата и время</li>" +
                "</ul>" +
                "</body></html>";
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return List.of(
                new Book("Война и мир", "Лев Толстой"),
                new Book("Преступление и наказание", "Фёдор Достоевский"),
                new Book("Гарри Поттер", "Дж. К. Роулинг")
        );
    }

    @GetMapping("/films")
    public List<Film> getFilms() {
        return List.of(
                new Film("Матрица", 1999),
                new Film("Начало", 2010),
                new Film("Интерстеллар", 2014)
        );
    }

    @GetMapping("/now")
    public Map<String, String> getNow() {
        return Map.of("datetime", LocalDateTime.now().toString());
    }
}

