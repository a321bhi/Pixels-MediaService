package com.pixels.mediaservices.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pixels.mediaservices.exception.PostNotFoundException;
import com.pixels.mediaservices.model.Media;
import com.pixels.mediaservices.model.Payload;
import com.pixels.mediaservices.service.MediaServiceImpl;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "*", allowedHeaders = { "*" })
public class MediaControllerService {

	@Autowired
	MediaServiceImpl mediaServiceImpl;

	// REST End point local
	@PostMapping("/media")
	public ResponseEntity<String> uploadMedia(@RequestBody Payload payload) {
		Media inputMedia = new Media(payload.getMediaId(), payload.getMediaDate(), payload.getMediaTags(),
				payload.getMediaCaption());
		inputMedia.setMediaEncodedData(payload.getImageAsBase64());
		mediaServiceImpl.addMedia(inputMedia);
		return new ResponseEntity<String>("data has been added", HttpStatus.OK);
	}

	// REST End point local
	@GetMapping("/media/{mediaId}")
	public Payload getMedia(@PathVariable String mediaId) {
		Optional<Media> media = mediaServiceImpl.findMediaById(mediaId);
		Media outputMedia;
		Payload payload;
		if (media.isEmpty()) {
			throw new PostNotFoundException();
		} else {
			outputMedia = media.get();
			payload = new Payload(outputMedia.getMediaId(), outputMedia.getMediaDate(), outputMedia.getMediaTags(),
						outputMedia.getMediaCaption(),outputMedia.getMediaEncodedData());
			return payload;
		}
	}

	// REST End point local
	@RequestMapping(value = "/getAllMedia", method = RequestMethod.POST)
	public List<Payload> getAllMediaOfOneUser(@RequestBody List<String> mediaIdList) throws PostNotFoundException {
		List<Payload> outputArrayOfPayload = new ArrayList<>();
		Media outputMedia;
		Payload payload;
		for (String mediaId : mediaIdList) {
			Optional<Media> media = mediaServiceImpl.findMediaById(mediaId);
			if (media.isEmpty()) {
				continue;
//				throw new PostNotFoundException();
			} else {
				outputMedia = media.get();
				payload = new Payload(outputMedia.getMediaId(), outputMedia.getMediaDate(), outputMedia.getMediaTags(),
							outputMedia.getMediaCaption(),outputMedia.getMediaEncodedData());
				outputArrayOfPayload.add(payload);
			}
		}
		return outputArrayOfPayload;
	}

	@DeleteMapping("/media/{mediaId}")
	public ResponseEntity<String> deleteOneMedia(@PathVariable String mediaId) {
		Optional<Media> optionalMedia = mediaServiceImpl.findMediaById(mediaId);
		if (!optionalMedia.isPresent()) {
			throw new PostNotFoundException();
		}
		mediaServiceImpl.deleteMedia(optionalMedia.get());
		return new ResponseEntity<String>("data has been deleted", HttpStatus.OK);
	}

	@PatchMapping("/media-caption")
	public ResponseEntity<String> updateCaption(@RequestBody Payload payload) {
		Optional<Media> mediaToBeUpdatedOpt = mediaServiceImpl.findMediaById(payload.getMediaId());
		if (!mediaToBeUpdatedOpt.isPresent()) {
			throw new PostNotFoundException();
		}
		Media mediaToBeUpdated = mediaToBeUpdatedOpt.get();
		mediaToBeUpdated.setMediaCaption(payload.getMediaCaption());
		mediaToBeUpdated.setMediaTags(payload.getMediaTags());
		mediaServiceImpl.addMedia(mediaToBeUpdated);
		return new ResponseEntity<String>("caption has been updated", HttpStatus.OK);
	}

}
