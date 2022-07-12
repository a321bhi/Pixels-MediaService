package com.pixels.mediaservices.doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pixels.mediaservices.model.Media;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {

}
