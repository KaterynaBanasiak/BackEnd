package org.example;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        ResponseEntity<ArrayList<CommentDto>> response = getCommentsFromServer("https://jsonplaceholder.typicode.com/comments");
        System.out.println("Статус код: " + response.getStatusCode());
        System.out.println("Заголовки: " + response.getHeaders());

        //PostDto[] listAllComments = response.getBody();
        ArrayList<CommentDto> listAllComments = response.getBody();
        for (var post: listAllComments){
            System.out.println(post);
            System.out.println();
        }
        // получение всех комментариев заданного поста
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер поста: ");
        int postId = scanner.nextInt();
        /*
        // начитка с сервера
        response = getCommentsFromServer("https://jsonplaceholder.typicode.com/comments?postId="+postId);
        ArrayList<CommentDto> listOfComments = response.getBody();


         */
        List<CommentDto> listOfComments = listAllComments.stream().filter(c -> c.getPostId().equals(postId)).toList();
        listOfComments.forEach(System.out::println);
    }

    private static ResponseEntity<ArrayList<CommentDto>> getCommentsFromServer(String uriString) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(uriString);
        RequestEntity<String> request = new RequestEntity<>(HttpMethod.GET,uri);
        //ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        //ResponseEntity<PostDto[]> response = restTemplate.exchange(request, PostDto[].class);
        ResponseEntity<ArrayList<CommentDto>> response =
                restTemplate.exchange(request, new ParameterizedTypeReference<ArrayList<CommentDto>>() { });
        //System.out.println(response.getBody());
        return response;
    }
}
