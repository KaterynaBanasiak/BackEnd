package de.ait;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class CommentsFetcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер поста (postId): ");
        int postId = scanner.nextInt();

        try {
            String url = "https://jsonplaceholder.typicode.com/comments?postId=" + postId;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            if (status == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder jsonResponse = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonResponse.append(line);
                }
                reader.close();

                // Распарсим JSON в список объектов
                Gson gson = new Gson();
                Type commentListType = new TypeToken<List<Comment>>() {}.getType();
                List<Comment> comments = gson.fromJson(jsonResponse.toString(), commentListType);

                // Выводим каждый комментарий красиво
                if (comments.isEmpty()) {
                    System.out.println("Нет комментариев для postId = " + postId);
                } else {
                    System.out.println("\nКомментарии к посту #" + postId + ":\n");
                    for (Comment comment : comments) {
                        System.out.println("Имя: " + comment.name);
                        System.out.println("Email: " + comment.email);
                        System.out.println("Комментарий:\n" + comment.body);
                        System.out.println("------------");
                    }
                }

            } else {
                System.out.println("Ошибка: код ответа сервера " + status);
            }

            connection.disconnect();

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
