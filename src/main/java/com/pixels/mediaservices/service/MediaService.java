package com.pixels.mediaservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.pixels.mediaservices.model.Media;

public interface MediaService {

	public Optional<Media> findMediaById(String mediaId);

	public Media addMedia(Media media);

	public void deleteMedia(Media media);

	public void deleteMediaById(String mediaId);

	public List<Media> findAllMedia();

	public Optional<List<Media>> findByMediaTags(List<String> mediaTags);

	public List<Media> findTagsByQueryTag(String queryTag);

	public List<Media> getAllTags();

	Page<Media> findByMediaTags(List<String> mediaTags, int page, int size, String sortDir, String sort);

}
