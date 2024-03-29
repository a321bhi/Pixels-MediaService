package com.pixels.mediaservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mediaId;
//	private Date mediaDate;
	private Date createdAt;
	private List<String> mediaTags = new ArrayList<>();
	private String mediaCaption;
	private String imageAsBase64;
	private Set<String> likedBy = new HashSet<>();
	private Set<MediaCommentDTO> mediaComments = new HashSet<>();
	private String mediaPostedBy;

	public String getMediaPostedBy() {
		return mediaPostedBy;
	}

	public void setMediaPostedBy(String mediaPostedBy) {
		this.mediaPostedBy = mediaPostedBy;
	}

	public MediaDTO(String mediaId, Date createdAt, List<String> mediaTags, String mediaCaption,
			String imageAsBase64) {
		super();
		this.mediaId = mediaId;
		this.createdAt = createdAt;
		this.mediaTags = mediaTags;
		this.mediaCaption = mediaCaption;
		this.imageAsBase64 = imageAsBase64;

	}

	public MediaDTO(Payload payload) {
		super();
		this.mediaId = payload.getMediaId();
		this.createdAt = payload.getCreatedAt();
		this.mediaTags = payload.getMediaTags();
		this.mediaCaption = payload.getMediaCaption();
		this.imageAsBase64 = payload.getImageAsBase64();
	}

	@Override
	public String toString() {
		return "ResponsePayload [mediaId=" + mediaId + ", createdAt=" + createdAt + ", mediaTags=" + mediaTags
				+ ", mediaCaption=" + mediaCaption + ", likedBy=" + likedBy + ", mediaComments=" + mediaComments + "]";
	}

	public Set<String> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Set<String> likedBy) {
		this.likedBy = likedBy;
	}

	public Set<MediaCommentDTO> getMediaComments() {
		return mediaComments;
	}

	public void setMediaComments(Set<MediaCommentDTO> mediaComments) {
		this.mediaComments = mediaComments;
	}

	public MediaDTO(String mediaId, Date createdAt, List<String> mediaTags, String mediaCaption,
			String imageAsBase64, Set<String> likedBy, Set<MediaCommentDTO> mediaComments) {
		super();
		this.mediaId = mediaId;
		this.createdAt = createdAt;
		this.mediaTags = mediaTags;
		this.mediaCaption = mediaCaption;
		this.imageAsBase64 = imageAsBase64;
		this.likedBy = likedBy;
		this.mediaComments = mediaComments;
	}

	public MediaDTO() {
		super();
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<String> getMediaTags() {
		return mediaTags;
	}

	public void setMediaTags(List<String> mediaTags) {
		this.mediaTags = mediaTags;
	}

	public String getMediaCaption() {
		return mediaCaption;
	}

	public void setMediaCaption(String mediaCaption) {
		this.mediaCaption = mediaCaption;
	}

	public String getImageAsBase64() {
		return imageAsBase64;
	}

	public void setImageAsBase64(String imageAsBase64) {
		this.imageAsBase64 = imageAsBase64;
	}
}
