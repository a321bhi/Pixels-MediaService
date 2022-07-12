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
		Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
		return mediaOptional;
	}

	@Override
	public Media addMedia(Media media) {
		Media savedMedia = mediaRepository.save(media);
		return savedMedia;
	}

	@Override
	public void deleteMediaById(String MediaId) {
		mediaRepository.deleteById(MediaId);
		return;
	}

	@Override
	public void deleteMedia(Media media) {
		mediaRepository.delete(media);
		return;
	}

	@Override
	public List<Media> findAllMedia() {
		return mediaRepository.findAll();
	}

}
