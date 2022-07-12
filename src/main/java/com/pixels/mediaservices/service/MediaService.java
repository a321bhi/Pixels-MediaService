package com.pixels.mediaservices.service;

import java.util.List;
import java.util.Optional;

import com.pixels.mediaservices.model.Media;


public interface MediaService {
	
	public Optional<Media> findMediaById(String mediaId);
	public Media addMedia(Media media);
	public void deleteMedia(Media media);
	public void deleteMediaById(String MediaId);
	public List<Media> findAllMedia();
}
