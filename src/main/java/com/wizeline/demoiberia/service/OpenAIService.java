package com.wizeline.demoiberia.service;

import com.wizeline.demoiberia.model.open.description.Message;
import com.wizeline.demoiberia.model.open.description.OpenDescriptionRequest;
import com.wizeline.demoiberia.model.open.description.OpenDescriptionResponse;
import com.wizeline.demoiberia.model.open.image.Data;
import com.wizeline.demoiberia.model.open.image.ImageRequest;
import com.wizeline.demoiberia.model.open.image.ImageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OpenAIService {

    private static final int NUMBER_IMAGES = 1;
    private static final String QUALITY = "hd";
    private static final String STYLE = "natural";
    private static final String SIZE = "1792x1024";
    private static final String IMAGE_ENDPOINT = "/images/generations";
    private static final String CHAT_ENDPOINT = "/chat/completions";
    private static final String ROLE = "user";

    @Value("${openai.url}")
    private String url;

    @Value("${openai.model.image}")
    private String imageModel;

    @Value("${openai.model.chat}")
    private String chatModel;

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

        final ImageRequest imageRequest = new ImageRequest(prompt, this.imageModel, NUMBER_IMAGES, QUALITY, STYLE, SIZE);

        final HttpEntity<ImageRequest> entity = new HttpEntity<>(imageRequest, headers);
        final ImageResponse result = this.restTemplate.postForObject(this.url + IMAGE_ENDPOINT, entity, ImageResponse.class);

        return Optional.ofNullable(result)
                .map(ImageResponse::getData)
                .flatMap(data -> data.stream()
                        .findFirst()
                        .map(Data::getUrl))
                .orElse("");
    }

    public String getDescription(final String prompt) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + this.apiKey);

        final OpenDescriptionRequest openDescriptionRequest = new OpenDescriptionRequest(this.chatModel, List.of(new Message(ROLE, prompt)));

        final HttpEntity<OpenDescriptionRequest> entity = new HttpEntity<>(openDescriptionRequest, headers);
        final OpenDescriptionResponse result = this.restTemplate.postForObject(this.url + CHAT_ENDPOINT, entity, OpenDescriptionResponse.class);

        return Optional.ofNullable(result)
                .map(OpenDescriptionResponse::getChoices)
                .flatMap(choices -> choices.stream()
                        .findFirst()
                        .filter(choice -> Objects.nonNull(choice.getMessage()))
                        .map(choice -> choice.getMessage().getContent()))
                .orElse("");
    }

}
