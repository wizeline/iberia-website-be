package com.wizeline.demoiberia.service;

import com.wizeline.demoiberia.model.dto.VideoResponse;
import com.wizeline.demoiberia.model.jpa.Video;
import com.wizeline.demoiberia.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(final VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    public List<VideoResponse> retrieveVideos() {
        final List<Video> videoList = this.videoRepository.findAll();

        return videoList.stream()
                .map(image -> new VideoResponse(image.getUrl(), image.getTag(), image.getDestination()))
                .toList();
    }

}
