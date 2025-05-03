package org.example;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://jsonplaceholder.typicode.com/posts");
        RequestEntity<String> request = new RequestEntity<>(HttpMethod.GET,uri);
        //ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        //ResponseEntity<PostDto[]> response = restTemplate.exchange(request, PostDto[].class);
        ResponseEntity<ArrayList<PostDto>> response =
                restTemplate.exchange(request, new ParameterizedTypeReference<ArrayList<PostDto>>() { });
        //System.out.println(response.getBody());
        System.out.println("Статус код: " + response.getStatusCode());
        System.out.println("Заголовки: " + response.getHeaders());

        //PostDto[] body = response.getBody();
        ArrayList<PostDto> body = response.getBody();
        for (PostDto post: body){
            System.out.println(post);
            System.out.println();
        }
    }
}
