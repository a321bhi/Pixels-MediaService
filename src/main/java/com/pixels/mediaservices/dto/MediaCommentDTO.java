package com.pixels.mediaservices.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MediaCommentDTO {
	private String commentId;

	private String commentContent;

	private Date createdAt = new Date();
	private String commentByUser;
	private String commentOnMediaId;
	private Set<String> commentLikedBy = new HashSet<>();
	private Set<MediaCommentDTO> commentsOnComment = new HashSet<>();

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

	public String getCommentByUser() {
		return commentByUser;
	}

	public void setCommentByUser(String commentByUser) {
		this.commentByUser = commentByUser;
	}

	public String getCommentOnMediaId() {
		return commentOnMediaId;
	}

	public void setCommentOnMediaId(String commentOnMediaId) {
		this.commentOnMediaId = commentOnMediaId;
	}

	public Set<String> getCommentLikedBy() {
		return commentLikedBy;
	}

	public void setCommentLikedBy(Set<String> commentLikedBy) {
		this.commentLikedBy = commentLikedBy;
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

	@Override
	public String toString() {
		return "MediaCommentDTO [commentId=" + commentId + ", commentContent=" + commentContent + ", createdAt="
				+ createdAt + ", commentByUser=" + commentByUser + ", commentOnMediaId=" + commentOnMediaId
				+ ", commentLikedBy=" + commentLikedBy + ", commentsOnComment=" + commentsOnComment + "]";
	}

}
