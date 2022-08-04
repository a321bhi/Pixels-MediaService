package com.pixels.mediaservices.doa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pixels.mediaservices.model.Media;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {
//	@Query("{ 'mediaTags':{$in:?0 }}")
//	public List<Media> findByMediaTags(String tag);
	@Query("{ 'mediaTags':/^?0/}")
	public List<Media> findByMediaTags(String tag);


	@Query(value = "{'mediaTags' : /^?0/}", fields = "{mediaTags : 1, _id : 0}")
	public List<Media> findMediaTagsByQuery(String queryTag);

	@Query(value = "{}", fields = "{mediaTags : 1, _id : 0}")
	public List<Media> findAllMediaTags();

// pageable queries
	public Page<Media> findAll(Pageable pageable);

	@Query("{ 'mediaTags':{$in:?0 }}")
	public Page<Media> findByMediaTags(List<String> mediaTags, Pageable pageable);
}
