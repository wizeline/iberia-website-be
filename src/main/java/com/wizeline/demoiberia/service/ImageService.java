package com.wizeline.demoiberia.service;

import com.wizeline.demoiberia.model.dto.ImageResponse;
import com.wizeline.demoiberia.model.dto.SaveRequest;
import com.wizeline.demoiberia.model.jpa.Image;
import com.wizeline.demoiberia.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(final ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String saveImage(final SaveRequest saveRequest) {

        final List<Image> byTagAndDestination = this.imageRepository.findByTagAndDestination(saveRequest.getTag(), saveRequest.getDestination());

        final Image image = byTagAndDestination.stream()
                .findFirst()
                .orElseGet(() -> new Image(saveRequest.getUrl(), saveRequest.getTag(), saveRequest.getDestination()));

        image.setUrl(saveRequest.getUrl());

        final Image saved = this.imageRepository.save(image);

        return saved.getUrl();

    }


    public List<ImageResponse> retrieveImages() {
        final List<Image> imageList = this.imageRepository.findAll();

        return imageList.stream()
                .map(image -> new ImageResponse(image.getUrl(), image.getTag(), image.getDestination()))
                .toList();


    }


}
