package com.andrew.tgserver.api;

import com.andrew.tgserver.entities.api_entities.AnswerEntity;
import com.andrew.tgserver.entities.api_entities.PictureEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnsplashApi {
    private final RestTemplate restTemplate;

    @Value("${unsplash.token}")
    private String token;
    public AnswerEntity getPicture(String category, int count) {
        String url = "https://api.unsplash.com/photos/random?client_id=" + token +
                "&query=" + category + "&count=" + count;

        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<List<PictureEntity>> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<PictureEntity>>() {});
            if (response.getStatusCode().is2xxSuccessful()) {
                return new AnswerEntity(200, response.getBody());
            } else {
                return new AnswerEntity(400, Collections.emptyList());
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            return new AnswerEntity(403, Collections.emptyList());
        }
    }
}
