package com.pixels.mediaservices.service;

import java.util.Optional;

import com.pixels.mediaservices.model.FeedPreference;

public interface FeedPreferenceService {
	public void setPreference(FeedPreference feedPreference);

	public Optional<FeedPreference> getPreferenceById(String username);

}
