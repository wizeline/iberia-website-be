package com.wizeline.demoiberia.controller;

import com.wizeline.demoiberia.model.dto.GenerateRequest;
import com.wizeline.demoiberia.model.dto.ImageResponse;
import com.wizeline.demoiberia.model.dto.SaveRequest;
import com.wizeline.demoiberia.service.FreePikService;
import com.wizeline.demoiberia.service.ImageService;
import com.wizeline.demoiberia.service.OpenAIService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ImagesController {

    @Autowired
    private HttpServletRequest httpServletRequest;

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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/generate",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<String> generateImage(@RequestBody final GenerateRequest generateRequest) {

        // This would be better using interfaces. Done using ifs for faster poc development.
        if (OPEN.equals(generateRequest.getEngine())) {
            return ResponseEntity.ok(this.openAIService.getImageUrl(generateRequest.getPrompt()));
        }
        if (FREE.equals(generateRequest.getEngine())) {
            return ResponseEntity.ok(this.freePikService.getImageUrl(generateRequest.getPrompt(), this.httpServletRequest));
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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/retrieve",
            produces = {"application/json"})
    ResponseEntity<List<ImageResponse>> retrieveImage() {

        final List<ImageResponse> imageResponses = this.imageService.retrieveImages();
        return ResponseEntity.ok(imageResponses);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/resources/freepik/{fileName}",
            produces = {"application/json"})
    public ResponseEntity<Resource> getFile(@PathVariable final String fileName) throws Exception {
        // Path to the PDF file rq 
        final Path path = Paths.get("/tmp/" + fileName);
        // Load the resource
        final Resource resource = new UrlResource(path.toUri());
        // Return ResponseEntity with PDF content type
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
