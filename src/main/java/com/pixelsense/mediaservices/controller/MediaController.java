//package com.pixelsense.mediaservices.controller;
//
//import java.io.IOException;
//import java.util.Base64;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pixelsense.mediaservices.exception.PostNotFoundException;
//import com.pixelsense.mediaservices.model.Media;
//import com.pixelsense.mediaservices.model.Payload;
//import com.pixelsense.mediaservices.service.MediaServiceImpl;
//
//@RestController
//@RequestMapping("/media")
//@CrossOrigin(origins = "*", allowedHeaders = { "Access-Control-Allow-Origin" })
//public class MediaController {
//
//	@Autowired
//	MediaServiceImpl mediaServiceImpl;
//
//	// REST End point local
////	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
////	public ResponseEntity<String> uploadMedia(@ModelAttribute Payload payload) {
////		Media inputMedia = new Media(payload.getMediaId(), payload.getMediaDate(), payload.getMediaTags(),
////				payload.getMediaCaption());
////
////		try {
////			inputMedia.setMediaEncodedData(Base64.getEncoder().encodeToString(payload.getImage().getBytes()));
////		} catch (IOException ioException) {
////			return new ResponseEntity<String>("upload failed", HttpStatus.BAD_REQUEST);
////		}
////		mediaServiceImpl.addMedia(inputMedia);
////		return new ResponseEntity<String>("data has been added", HttpStatus.OK);
////	}
//
//	// REST End point local
////	@RequestMapping(value = "/get", method = RequestMethod.POST)
////	public Payload getMedia(@RequestParam String mediaId) {
////		Optional<Media> media = mediaServiceImpl.findMediaById(mediaId);
////
////		Media outputMedia;
////		if (media.isEmpty()) {
////			throw new PostNotFoundException();
////		} else {
////			outputMedia = media.get();
////			Payload payload = new Payload(outputMedia.getMediaId(), outputMedia.getMediaDate(),
////					outputMedia.getMediaTags(), outputMedia.getMediaCaption(), outputMedia.getMediaEncodedData());
////			return payload;
////		}
////	}
//	
////11111111111111111111111111111111111111111111111
//
////	@GetMapping("/all")
////	public List<Media> getUserInfo() {
////
////		return new ResponseEntity<String>("Hello", HttpStatus.OK);
////	}
//
////	
////	@GetMapping("/{userName}")
////	public PixelSenseUser getUserInfo(@PathVariable String userName) {
////		Optional<PixelSenseUser> opt = userService.findUser(userName);
////		if(!opt.isPresent()) {
////			throw new UserNameNotFoundException();
////		}
////		return opt.get();
////	}
////
////	@DeleteMapping("/delete/{userName}")
////	public ResponseEntity<String> deleteUser(@PathVariable String userName) {
////		userService.deleteUser(userName);
////		return new ResponseEntity<String>(userName + " removed successfully", HttpStatus.OK);
////	}
////
////	@PutMapping("/update/{userName}")
////	public ResponseEntity<String> updateUser(@PathVariable String userName, @RequestBody PixelSenseUser updatedUser) {
////		userService.updateUser(userName, updatedUser);
////		return new ResponseEntity<String>(updatedUser.getFirstName() + "'s data has been updated", HttpStatus.OK);
////	}
//
//}
