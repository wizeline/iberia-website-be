package com.wizeline.demoiberia.controller;

import com.wizeline.demoiberia.model.dto.VideoResponse;
import com.wizeline.demoiberia.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoController {

    private final VideoService videoService;

    public VideoController(final VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping(value = "/videos",
            produces = {"application/json"})
    ResponseEntity<List<VideoResponse>> retrieveVideos() {

        final List<VideoResponse> videoResponses = this.videoService.retrieveVideos();
        return ResponseEntity.ok(videoResponses);
    }

}
