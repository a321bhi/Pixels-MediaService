package com.pixelsense.mediaservices.doa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pixelsense.mediaservices.model.Media;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {

}
