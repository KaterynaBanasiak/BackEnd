package de.ait;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        String imgUrl = "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";
        String lang = "en,de,ru";
        String token = "Basic ....."; /// !! ваш токен
        String url ="https://api.imagga.com/v2/tags";

        RestTemplate restTemplate = new RestTemplate();

        // Установить http-заголовки
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        // получить uri строку UriComponentsBuilder.fromHttpUrl(url)
        // Deprecated.  as of 6.2, in favor of fromUriString(String)

        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("image_url", imgUrl)
                .queryParam("language", lang)
                .build()
                .toUri();

        // создали RequestEntity
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, uri);

        //вызвали restTemplate
        ResponseEntity<TagsResponseDto> response = restTemplate.exchange(request, TagsResponseDto.class);

        response.getBody().getResult().getTags().forEach(System.out::println);
        /*
        TagDto[] tags = response.getBody().getResult().getTags();
        for (var el:tags){
            System.out.println(el);
        }

         */


    }
}
