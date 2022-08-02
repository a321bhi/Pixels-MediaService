package com.pixels.mediaservices.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MediaOracleDTO {
	private String mediaId;
	private Date createdAt = new Date();
	private PixelsUserDTO mediaPostedBy;
	private Set<PixelsUserDTO> likedBy = new HashSet<>();
	private Set<MediaCommentDTO> mediaComments = new HashSet<>();

	public void removeLikedByAndComments() {
		this.likedBy.clear();
		this.mediaComments.clear();
	}

	public MediaOracleDTO() {
		super();
	}

	public MediaOracleDTO(Date createdAt, PixelsUserDTO mediaPostedBy) {
		super();
		this.createdAt = createdAt;
		this.mediaPostedBy = mediaPostedBy;
	}

	public MediaOracleDTO(PixelsUserDTO mediaPostedBy, Set<PixelsUserDTO> likedBy, Set<MediaCommentDTO> mediaComments) {
		super();
		this.mediaPostedBy = mediaPostedBy;
		this.likedBy = likedBy;
		this.mediaComments = mediaComments;
	}

	@Override
	public String toString() {
		return "Media [mediaPostedBy=" + mediaPostedBy + ", likedBy=" + likedBy + ", mediaComments=" + mediaComments
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, mediaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaOracleDTO other = (MediaOracleDTO) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(mediaId, other.mediaId);
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

	public PixelsUserDTO getMediaPostedBy() {
		return mediaPostedBy;
	}

	public void setMediaPostedBy(PixelsUserDTO mediaPostedBy) {
		this.mediaPostedBy = mediaPostedBy;
	}

	public Set<PixelsUserDTO> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Set<PixelsUserDTO> likedBy) {
		this.likedBy = likedBy;
	}

	public Set<MediaCommentDTO> getMediaComments() {
		return mediaComments;
	}

	public void setMediaComments(Set<MediaCommentDTO> mediaComments) {
		this.mediaComments = mediaComments;
	}

}
