package com.pixels.mediaservices.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MediaCommentDTO {
	private String commentId;

	private String commentContent;

	private Date createdAt = new Date();
	private PixelsUserDTO commentByUser;
	private MediaOracleDTO commentOnMediaId;
	private Set<PixelsUserDTO> commentLikedBy = new HashSet<>();
	private MediaCommentDTO commentOnCommentId;
	private Set<MediaCommentDTO> commentsOnComment = new HashSet<>();

	public MediaCommentDTO getCommentOnCommentId() {
		return commentOnCommentId;
	}

	public void setCommentOnCommentId(MediaCommentDTO commentOnCommentId) {
		this.commentOnCommentId = commentOnCommentId;
	}

	public Set<MediaCommentDTO> getCommentsOnComment() {
		return commentsOnComment;
	}

	public void setCommentsOnComment(Set<MediaCommentDTO> commentsOnComment) {
		this.commentsOnComment = commentsOnComment;
	}

	public MediaCommentDTO() {
		super();
	}

	public MediaCommentDTO(String commentContent, PixelsUserDTO commentByUser, MediaCommentDTO commentOnCommentId) {
		super();
		this.commentContent = commentContent;
		this.commentByUser = commentByUser;
		this.commentOnCommentId = commentOnCommentId;
	}

	public MediaCommentDTO(String commentContent, PixelsUserDTO commentByUser, MediaOracleDTO commentOnMediaId) {
		super();
		this.commentContent = commentContent;
		this.commentByUser = commentByUser;
		this.commentOnMediaId = commentOnMediaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentId, createdAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MediaCommentDTO other = (MediaCommentDTO) obj;
		return Objects.equals(commentId, other.commentId) && Objects.equals(createdAt, other.createdAt);
	}

	@Override
	public String toString() {
		return "MediaComment [commentId=" + commentId + ", commentContent=" + commentContent + ", createdAt="
				+ createdAt + ", commentByUser=" + commentByUser + ", commentOnMediaId=" + commentOnMediaId + "]";
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public PixelsUserDTO getCommentByUser() {
		return this.commentByUser;
	}

	public void setCommentByUser(PixelsUserDTO commentByUser) {
		this.commentByUser = commentByUser;
	}

	public MediaOracleDTO getCommentOnMediaId() {
		return commentOnMediaId;
	}

	public void setCommentOnMediaId(MediaOracleDTO commentOnMediaId) {
		this.commentOnMediaId = commentOnMediaId;
	}

	public Set<PixelsUserDTO> getCommentLikedBy() {
		return commentLikedBy;
	}

	public void setCommentLikedBy(Set<PixelsUserDTO> commentLikedBy) {
		this.commentLikedBy = commentLikedBy;
	}

}
