package com.wizeline.demoiberia.repository;

import com.wizeline.demoiberia.model.jpa.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByTagAndDestination(String tag, String destination);
}
