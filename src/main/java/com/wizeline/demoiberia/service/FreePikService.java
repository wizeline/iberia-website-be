package com.wizeline.demoiberia.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wizeline.demoiberia.exception.ImageGenerationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FreePikService {

    @Value("${freepik.key}")
    private String apiKey;

    public String getImageUrl(final String prompt, final HttpServletRequest httpServletRequest) {
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
        FileOutputStream fos = null;
        final String pathOutputFile = File.separator + "tmp" + File.separator + fileName;
        try {
            fos = new FileOutputStream(pathOutputFile);
            fos.write(base64Byte);

        } catch (final Exception e) {
            throw new ImageGenerationException("Error generating images", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (final IOException e) {
                    System.out.println("Unable to close " + pathOutputFile);
                }
            }
        }

        return httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + "/resources/freepik/" + fileName;
    }


}
