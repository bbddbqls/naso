package com.naso.post;

import java.sql.Timestamp;

public class PostDTO {
	private String pNum;
	private String userId;
	private String expose;
	private String category;
	private Timestamp datetimeCreated;
	private String title;
	private String mediaOriginal;
	private String mediaS3;
	private String content;
	private int viewCount;
	private int likeCount;

	public String getPNum() {
		return pNum;
	}

	public void setPNum(String pNum) {
		this.pNum = pNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExpose() {
		return expose;
	}

	public void setExpose(String expose) {
		this.expose = expose;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getDatetimeCreated() {
		return datetimeCreated;
	}

	public void setDatetimeCreated(Timestamp datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaOriginal() {
		return mediaOriginal;
	}

	public void setMediaOriginal(String mediaOriginal) {
		this.mediaOriginal = mediaOriginal;
	}

	public String getMediaS3() {
		return mediaS3;
	}

	public void setMediaS3(String mediaS3) {
		this.mediaS3 = mediaS3;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}

