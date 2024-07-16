package com.wizeline.demoiberia.service;

import com.wizeline.demoiberia.model.open.Data;
import com.wizeline.demoiberia.model.open.Request;
import com.wizeline.demoiberia.model.open.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OpenAIService {

    private static final int NUMBER_IMAGES = 1;
    private static final String QUALITY = "hd";
    private static final String STYLE = "natural";
    private static final String SIZE = "1792x1024";

    @Value("${openai.url}")
    private String url;

    @Value("${openai.model}")
    private String imageModel;

    @Value("${openai.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public OpenAIService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getImageUrl(final String prompt) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + this.apiKey);

        final Request request = new Request(prompt, this.imageModel, NUMBER_IMAGES, QUALITY, STYLE, SIZE);

        final HttpEntity<Request> entity = new HttpEntity<>(request, headers);
        final Response result = this.restTemplate.postForObject(this.url, entity, Response.class);

        return Optional.ofNullable(result)
                .map(Response::getData)
                .flatMap(data -> data.stream()
                        .findFirst()
                        .map(Data::getUrl))
                .orElse("");
    }

}
