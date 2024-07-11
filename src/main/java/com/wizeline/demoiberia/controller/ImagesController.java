package com.wizeline.demoiberia.controller;

import com.wizeline.demoiberia.model.dto.GenerateRequest;
import com.wizeline.demoiberia.model.dto.ImageResponse;
import com.wizeline.demoiberia.model.dto.SaveRequest;
import com.wizeline.demoiberia.service.FreePikService;
import com.wizeline.demoiberia.service.ImageService;
import com.wizeline.demoiberia.service.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImagesController {


    private static final String OPEN = "open";
    private static final String FREE = "free";
    private final ImageService imageService;
    private final OpenAIService openAIService;
    private final FreePikService freePikService;

    public ImagesController(final ImageService imageService, final OpenAIService openAIService, final FreePikService freePikService) {
        this.imageService = imageService;
        this.openAIService = openAIService;
        this.freePikService = freePikService;
    }

    @PostMapping(value = "/generate",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<String> generateImage(@RequestBody final GenerateRequest generateRequest) {

        // This would be better using interfaces. Done using ifs for faster poc development.
        if (OPEN.equals(generateRequest.getEngine())) {
            return ResponseEntity.ok(this.openAIService.getImageUrl(generateRequest.getPrompt()));
        }
        if (FREE.equals(generateRequest.getEngine())) {
            return ResponseEntity.ok(this.freePikService.getImageUrl(generateRequest.getPrompt()));
        }


        return ResponseEntity.badRequest()
                .body("unexpected engine. Supported values are open and free");
    }


    @PostMapping(value = "/save",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<String> saveImage(@RequestBody final SaveRequest saveRequest) {

        final String url = this.imageService.saveImage(saveRequest);
        return ResponseEntity.ok(url);
    }

    @GetMapping(value = "/retrieve",
            produces = {"application/json"})
    ResponseEntity<List<ImageResponse>> saveImage() {

        final List<ImageResponse> imageResponses = this.imageService.retrieveImages();
        return ResponseEntity.ok(imageResponses);
    }


}
