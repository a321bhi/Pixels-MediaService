package com.pixels.mediaservices.doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pixels.mediaservices.model.FeedPreference;

@Repository
public interface FeedPreferenceRepository extends MongoRepository<FeedPreference, String> {

}
