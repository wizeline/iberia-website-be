package com.wizeline.demoiberia.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wizeline.demoiberia.exception.ImageGenerationException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.buf.UriUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class FreePikService {

    @Value("${freepik.key}")
    private String apiKey;

    private final ImageUploadService imageUploadService;

    public FreePikService(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }
    public String searchImageUrl(final String prompt, final HttpServletRequest httpServletRequest) {
        final RestClient customClient = RestClient.builder()
                .defaultHeader("x-freepik-api-key", this.apiKey)
                .build();

        final String result = customClient.get().uri(
                UriComponentsBuilder.fromUriString(  "https://api.freepik.com/v1/resources")
                        .queryParam("term", prompt).toUriString()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().body(String.class);

        final JsonObject jsonObject = JsonParser.parseString(result)
                .getAsJsonObject();
        
        return jsonObject.getAsJsonArray("data")
                .get(0).getAsJsonObject()
                .get("image")
                .getAsJsonObject()
                .get("source")
                .getAsJsonObject()
                .get("url")
                .getAsString();
    }
    
    public String generateImageUrl(final String prompt, final HttpServletRequest httpServletRequest) {
        final RestClient customClient = RestClient.builder()
                .defaultHeader("x-freepik-api-key", this.apiKey)
                .build();

        final String result = customClient.post().uri("https://api.freepik.com/v1/ai/text-to-image")
                .body("{\"prompt\":\"" + prompt + "\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve().body(String.class);

        final JsonObject jsonObject = JsonParser.parseString(result)
                .getAsJsonObject();

        final String base64String = jsonObject.getAsJsonArray("data").get(0).getAsJsonObject().get("base64").getAsString();
        final byte[] base64Byte = Base64Coder.decode(base64String);
        final String fileName = "freepik" + System.nanoTime() + ".jpg";

        return imageUploadService.uploadImage(base64Byte, fileName);
    }


}
