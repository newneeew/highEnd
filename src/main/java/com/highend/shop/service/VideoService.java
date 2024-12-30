package com.highend.shop.service;

import com.highend.shop.domain.Video;
import com.highend.shop.dto.AddVideoRequest;
import com.highend.shop.dto.UpdateVideoRequest;
import com.highend.shop.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public Video save(AddVideoRequest request) {
        return videoRepository.save(request.toEntity());
    }

    public List<Video> findAllVideo() {
        return videoRepository.findAll();
    }

    public Video findById(long id) {
        Video video = videoRepository.findById(id).orElse(new Video());
        return video;
    }

    public void delete(long id) {
        videoRepository.deleteById(id);
    }

    @Transactional
    public Video update(long id, UpdateVideoRequest request) {
        Video video = videoRepository.findById(id).orElse(new Video());
        video.update(request.getUrl(), request.getTitle(), request.getPublish_at());
        return video;
    }
}
