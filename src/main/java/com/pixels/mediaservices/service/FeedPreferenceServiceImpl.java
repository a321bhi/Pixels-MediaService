package com.pixels.mediaservices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixels.mediaservices.doa.FeedPreferenceRepository;
import com.pixels.mediaservices.model.FeedPreference;

@Service
public class FeedPreferenceServiceImpl implements FeedPreferenceService {

	@Autowired
	FeedPreferenceRepository feedPreferenceRepository;

	@Override
	public void setPreference(FeedPreference feedPreference) {
		feedPreferenceRepository.save(feedPreference);
	}

	@Override
	public Optional<FeedPreference> getPreferenceById(String username) {
		return feedPreferenceRepository.findById(username);
	}
}
