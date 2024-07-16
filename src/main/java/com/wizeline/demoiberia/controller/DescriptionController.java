package com.wizeline.demoiberia.controller;

import com.wizeline.demoiberia.model.dto.DescriptionRequest;
import com.wizeline.demoiberia.service.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DescriptionController {

    private final OpenAIService openAIService;

    public DescriptionController(final OpenAIService openAIService) {
        this.openAIService = openAIService;
    }


    @PostMapping(value = "/description",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<String> generateDescription(@RequestBody final DescriptionRequest descriptionRequest) {

        return ResponseEntity.ok(this.openAIService.getDescription(descriptionRequest.getPrompt()));

    }


}
