package com.wizeline.demoiberia.repository;

import com.wizeline.demoiberia.model.jpa.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByTagAndDestination(String tag, String destination);
}
