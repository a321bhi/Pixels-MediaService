package com.pixels.mediaservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixels.mediaservices.doa.MediaRepository;
import com.pixels.mediaservices.model.Media;


@Service
public class MediaServiceImpl implements MediaService{

	@Autowired
	MediaRepository mediaRepository;
	

	@Override
	public Optional<Media> findMediaById(String mediaId) {
		return mediaRepository.findById(mediaId);
	}

	@Override
	public Media addMedia(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public void deleteMediaById(String mediaId) {
		mediaRepository.deleteById(mediaId);
	}

	@Override
	public void deleteMedia(Media media) {
		mediaRepository.delete(media);
	}

	@Override
	public List<Media> findAllMedia() {
		return mediaRepository.findAll();
	}

}
