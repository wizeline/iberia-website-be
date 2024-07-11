package com.wizeline.demoiberia.controller;

import com.wizeline.demoiberia.model.dto.GenerateRequest;
import com.wizeline.demoiberia.model.dto.ImageResponse;
import com.wizeline.demoiberia.model.dto.SaveRequest;
import com.wizeline.demoiberia.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImagesController {


    private final ImageService imageService;

    public ImagesController(final ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/generate",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<String> generateImage(@RequestBody final GenerateRequest generateRequest) {
        return ResponseEntity.ok("https://wizeline.okta.com");
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
