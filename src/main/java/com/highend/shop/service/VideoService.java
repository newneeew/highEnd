package com.highend.shop.service;

import com.highend.shop.domain.Video;
import com.highend.shop.dto.AddVideoRequest;
import com.highend.shop.dto.UpdateVideoRequest;
import com.highend.shop.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<Video> video = videoRepository.findById(id);
        if (video == null) {
            return null;
        }
        return video.get();
    }

    public void delete(long id) {
        videoRepository.deleteById(id);
    }

    @Transactional
    public Video update(long id, UpdateVideoRequest request) {
        Optional<Video> video = videoRepository.findById(id);
        if (video == null) {
            return null;
        }
        Video updateVideo = video.get();
        updateVideo.update(request.getUrl(), request.getTitle(), request.getPublish_at());
        return updateVideo;
    }
}
