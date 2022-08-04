package com.pixels.mediaservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pixels.mediaservices.doa.MediaRepository;
import com.pixels.mediaservices.model.Media;

@Service
public class MediaServiceImpl implements MediaService {

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

//	@Override
//	public Optional<List<Media>> findByMediaTags(List<String> mediaTags) {
//		return mediaRepository.findByMediaTags(mediaTags);
//	}

	@Override
	public List<Media> findTagsByQueryTag(String queryTag) {
		return mediaRepository.findMediaTagsByQuery(queryTag);
	}

	@Override
	public List<Media> getAllTags() {
		return mediaRepository.findAllMediaTags();
	}

	// pageable
	@Override
	public Page<Media> findByMediaTags(List<String> mediaTags, int page, int size, String sortDir, String sort) {
		PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

		return mediaRepository.findByMediaTags(mediaTags, pageReq);

	}

	public List<Media> findByMediaTagsSearch(String tag) {
		return mediaRepository.findByMediaTags(tag);
	}

}
