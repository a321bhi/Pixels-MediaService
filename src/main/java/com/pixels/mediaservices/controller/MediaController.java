package com.pixels.mediaservices.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.pixels.mediaservices.dto.MediaDTO;
import com.pixels.mediaservices.dto.Payload;
import com.pixels.mediaservices.dto.SearchResult;
import com.pixels.mediaservices.exception.PostNotFoundException;
import com.pixels.mediaservices.model.FeedPreference;
import com.pixels.mediaservices.model.Media;
import com.pixels.mediaservices.service.FeedPreferenceServiceImpl;
import com.pixels.mediaservices.service.MediaServiceImpl;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/service")
public class MediaController {

	@Autowired
	private WebClient.Builder webClientBuilder;
	private String baseUrl = "http://userservice";
	@Autowired
	FeedPreferenceServiceImpl feedPreferenceServiceImpl;
	@Autowired
	MediaServiceImpl mediaServiceImpl;

	// REST End point local
	@PostMapping("/media")
	public ResponseEntity<String> uploadMedia(@RequestBody Payload payload) {
		Media inputMedia = new Media(payload.getMediaId(), payload.getMediaDate(), payload.getMediaTags(),
				payload.getMediaCaption());
		inputMedia.setMediaEncodedData(payload.getImageAsBase64());
		mediaServiceImpl.addMedia(inputMedia);
		return new ResponseEntity<>("data has been added", HttpStatus.OK);
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
					outputMedia.getMediaCaption(), outputMedia.getMediaEncodedData());
			return payload;
		}
	}

	// REST End point local
	@PostMapping("/getAllMedia")
	public List<Payload> getAllMediaOfOneUser(@RequestBody List<String> mediaIdList) throws PostNotFoundException {
		List<Payload> outputArrayOfPayload = new ArrayList<>();
		Media outputMedia;
		Payload payload;
		for (String mediaId : mediaIdList) {
			Optional<Media> media = mediaServiceImpl.findMediaById(mediaId);
			if (media.isPresent()) {
				outputMedia = media.get();
				payload = new Payload(outputMedia.getMediaId(), outputMedia.getMediaDate(), outputMedia.getMediaTags(),
						outputMedia.getMediaCaption(), outputMedia.getMediaEncodedData());
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
		return new ResponseEntity<>("data has been deleted", HttpStatus.OK);
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
		return new ResponseEntity<>("caption has been updated", HttpStatus.OK);
	}

	@PostMapping("/feed-preference")
	public ResponseEntity<String> setPreference(@RequestBody FeedPreference feedPreference) {
		feedPreferenceServiceImpl.setPreference(feedPreference);
		return new ResponseEntity<>("preference set", HttpStatus.OK);
	}

	@GetMapping("/feed-preference/{username}")
	public FeedPreference updatePreference(@PathVariable String username) {

		Optional<FeedPreference> feedPreferenceByUserIdOpt = feedPreferenceServiceImpl.getPreferenceById(username);
		if (feedPreferenceByUserIdOpt.isPresent()) {
			return feedPreferenceByUserIdOpt.get();
		}
		return new FeedPreference(username);
	}

	@GetMapping("/search/{query}")
	public SearchResult getSearchResults(@PathVariable String query) {
		List<Media> mediaTagsOutput = mediaServiceImpl.findTagsByQueryTag(query);
		List<String> mediaTags = new ArrayList<>();
		mediaTagsOutput.stream().map(Media::getMediaTags).forEach(t -> {
			for (String checkingForQueryString : t) {
				if (checkingForQueryString.contains(query) && !mediaTags.contains(query)) {
					mediaTags.add(checkingForQueryString);
				}
			}
		});
		SearchResult searchResult = new SearchResult(mediaTags);
		return searchResult;
	}

	@GetMapping("/feed-paginated/")
	public List<MediaDTO> getFeedsPaginated(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("sortDir") String sortDir, @RequestParam("sort") String sort,
			@RequestParam("tags") List<String> tags, @RequestHeader("Authorization") String authorizationHeader) {
		Page<Media> outputFeedPage = mediaServiceImpl.findByMediaTags(tags, page, size, sortDir, sort);
		List<Media> outputFeed = outputFeedPage.getContent();
		List<MediaDTO> listOfResponsePayload = new ArrayList<>();
		if (!outputFeed.isEmpty()) {

			List<Payload> outputPayload = new ArrayList<>();
			for (Media output : outputFeed) {

				Payload payload = new Payload(output.getMediaId(), output.getMediaDate(), output.getMediaTags(),
						output.getMediaCaption(), output.getMediaEncodedData());
				outputPayload.add(payload);
			}

			MediaDTO temporaryResponsePayload;

			ExchangeStrategies strategies = ExchangeStrategies.builder()
					.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(-1)).build();
			WebClient webClient = webClientBuilder.exchangeStrategies(strategies).baseUrl(baseUrl).build();

			for (Payload tempPayload : outputPayload) {
				temporaryResponsePayload = new MediaDTO(tempPayload);
				Mono<MediaDTO> response = webClient.get().uri("/user/mappings/" + tempPayload.getMediaId())
						.header("Authorization", authorizationHeader).retrieve().bodyToMono(MediaDTO.class);
				MediaDTO feedResponse = response.block();

				temporaryResponsePayload.setLikedBy(feedResponse.getLikedBy());
				temporaryResponsePayload.setMediaPostedBy(feedResponse.getMediaPostedBy());
				temporaryResponsePayload.setMediaComments(feedResponse.getMediaComments());

				listOfResponsePayload.add(temporaryResponsePayload);
			}
			return listOfResponsePayload;
		} else {
			return new ArrayList<>();
		}
	}

	@GetMapping("/feed-paginated-search/")
	public List<MediaDTO> getFeedsPaginatedSearch(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("sortDir") String sortDir, @RequestParam("sort") String sort,
			@RequestParam("tags") List<String> tags, @RequestHeader("Authorization") String authorizationHeader) {
		Page<Media> outputFeedPage = mediaServiceImpl.findByMediaTags(tags, page, size, sortDir, sort);
		List<Media> outputFeed = outputFeedPage.getContent();
		List<MediaDTO> listOfResponsePayload = new ArrayList<>();
		if (!outputFeed.isEmpty()) {

			List<Payload> outputPayload = new ArrayList<>();
			for (Media output : outputFeed) {

				Payload payload = new Payload(output.getMediaId(), output.getMediaDate(), output.getMediaTags(),
						output.getMediaCaption(), output.getMediaEncodedData());
				outputPayload.add(payload);
			}

			MediaDTO temporaryResponsePayload;

			ExchangeStrategies strategies = ExchangeStrategies.builder()
					.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(-1)).build();
			WebClient webClient = webClientBuilder.exchangeStrategies(strategies).baseUrl(baseUrl).build();

			for (Payload tempPayload : outputPayload) {
				temporaryResponsePayload = new MediaDTO(tempPayload);
				Mono<MediaDTO> response = webClient.get().uri("/user/mappings/" + tempPayload.getMediaId())
						.header("Authorization", authorizationHeader).retrieve().bodyToMono(MediaDTO.class);
				MediaDTO feedResponse = response.block();

				temporaryResponsePayload.setLikedBy(feedResponse.getLikedBy());
				temporaryResponsePayload.setMediaPostedBy(feedResponse.getMediaPostedBy());
				temporaryResponsePayload.setMediaComments(feedResponse.getMediaComments());
				listOfResponsePayload.add(temporaryResponsePayload);
			}
			return listOfResponsePayload;
		} else {
			return new ArrayList<>();
		}
	}

	@GetMapping("/feed-search/{tag}")
	public List<MediaDTO> getFeedsSearch(@PathVariable String tag,
			@RequestHeader("Authorization") String authorizationHeader) {
		List<Media> outputFeed = mediaServiceImpl.findByMediaTagsSearch(tag);
		List<MediaDTO> listOfResponsePayload = new ArrayList<>();
		if (!outputFeed.isEmpty()) {

			List<Payload> outputPayload = new ArrayList<>();
			for (Media output : outputFeed) {

				Payload payload = new Payload(output.getMediaId(), output.getMediaDate(), output.getMediaTags(),
						output.getMediaCaption(), output.getMediaEncodedData());
				outputPayload.add(payload);
			}

			MediaDTO temporaryResponsePayload;

			ExchangeStrategies strategies = ExchangeStrategies.builder()
					.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(-1)).build();
			WebClient webClient = webClientBuilder.exchangeStrategies(strategies).baseUrl(baseUrl).build();

			for (Payload tempPayload : outputPayload) {
				temporaryResponsePayload = new MediaDTO(tempPayload);
				Mono<MediaDTO> response = webClient.get().uri("/user/mappings/" + tempPayload.getMediaId())
						.header("Authorization", authorizationHeader).retrieve().bodyToMono(MediaDTO.class);
				MediaDTO feedResponse = response.block();

				temporaryResponsePayload.setLikedBy(feedResponse.getLikedBy());
				temporaryResponsePayload.setMediaPostedBy(feedResponse.getMediaPostedBy());
				temporaryResponsePayload.setMediaComments(feedResponse.getMediaComments());
				listOfResponsePayload.add(temporaryResponsePayload);
			}
			return listOfResponsePayload;
		} else {
			return new ArrayList<>();
		}
	}

	@GetMapping(value = "/media-for-update/{mediaId}")
	public MediaDTO getTags(@PathVariable String mediaId, @RequestHeader("Authorization") String authorizationHeader) {
		Optional<Media> outputOpt = mediaServiceImpl.findMediaById(mediaId);
		if (outputOpt.isPresent()) {
			Media outputMedia = outputOpt.get();
			Payload payload = new Payload(outputMedia.getMediaId(), outputMedia.getMediaDate(),
					outputMedia.getMediaTags(), outputMedia.getMediaCaption(), outputMedia.getMediaEncodedData());

			MediaDTO temporaryResponsePayload;

			ExchangeStrategies strategies = ExchangeStrategies.builder()
					.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(-1)).build();
			WebClient webClient = webClientBuilder.exchangeStrategies(strategies).baseUrl(baseUrl).build();

			temporaryResponsePayload = new MediaDTO(payload);
			Mono<MediaDTO> response = webClient.get().uri("/user/mappings/" + outputMedia.getMediaId())
					.header("Authorization", authorizationHeader).retrieve()
					.bodyToMono(new ParameterizedTypeReference<MediaDTO>() {
					});
			MediaDTO feedResponse = response.block();

			temporaryResponsePayload.setLikedBy(feedResponse.getLikedBy());
			temporaryResponsePayload.setMediaPostedBy(feedResponse.getMediaPostedBy());
			temporaryResponsePayload.setMediaComments(feedResponse.getMediaComments());

			return temporaryResponsePayload;
		} else {
			return new MediaDTO();
		}
	}

	@GetMapping(value = "/tags")
	public List<String> getTags() {
		List<String> outputPayload = new ArrayList<>();
		List<Media> output = mediaServiceImpl.getAllTags();
		for (Media media : output) {
			outputPayload.addAll(media.getMediaTags());
		}
		return outputPayload;
	}
}
