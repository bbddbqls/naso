package com.naso.post;

import java.sql.Timestamp;

public class PostDTO {
	
	public class PostItemDTO{
		private String pNum;
		private String mNum;
		private String userId;
		private String expose;
		private String category;
		private Timestamp datetimeCreated;
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

		public String getMNum() {
			return mNum;
		}

		public void setMNum(String mNum) {
			this.mNum = mNum;
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
	
	//리플
	public class PostReplyDTO {
	    private String rNum;
	    private String pNum;
	    private String mNum;
	    private String mUserId;
	    private String rContent;
	    private Timestamp rDatetimeCreated;

	    public PostReplyDTO() {
	    	
	    }
	    
	    public PostReplyDTO(String rNum, String pNum, String mNum, String rContent, Timestamp rDatetimeCreated) {
	        this.rNum = rNum;
	        this.pNum = pNum;
	        this.mNum = mNum;
	        this.rContent = rContent;
	        this.rDatetimeCreated = rDatetimeCreated;
	    }

	    public String getRNum() {
	        return rNum;
	    }

	    public void setRNum(String rNum) {
	        this.rNum = rNum;
	    }

	    public String getPNum() {
	        return pNum;
	    }

	    public void setPNum(String pNum) {
	        this.pNum = pNum;
	    }

	    public String getMNum() {
	        return mNum;
	    }

	    public void setMNum(String mNum) {
	        this.mNum = mNum;
	    }
	    public String getMUserId() {
	        return mUserId;
	    }

	    public void setMUserId(String mUserId) {
	        this.mUserId = mUserId;
	    }

	    public String getRContent() {
	        return rContent;
	    }

	    public void setRContent(String rContent) {
	        this.rContent = rContent;
	    }

	    public Timestamp getRDatetimeCreated() {
	        return rDatetimeCreated;
	    }

	    public void setRDatetimeCreated(Timestamp rDatetimeCreated) {
	        this.rDatetimeCreated = rDatetimeCreated;
	    }
	}
	//다이어리 DTO
	public class DiaryDTO {
	    private String dNum;
	    private String mNum;
	    private Timestamp datetimeCreated;
	    private String title;
	    private String content;

	   public DiaryDTO () {
		  
	   }
	    
	    public DiaryDTO(String dNum, String mNum, Timestamp datetimeCreated, String title, String content) {
	        this.dNum = dNum;
	        this.mNum = mNum;
	        this.datetimeCreated = datetimeCreated;
	        this.title = title;
	        this.content = content;
	    }

	    public String getDNum() {
	        return dNum;
	    }

	    public void setDNum(String dNum) {
	        this.dNum = dNum;
	    }

	    public String getMNum() {
	        return mNum;
	    }

	    public void setMNum(String mNum) {
	        this.mNum = mNum;
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

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }
	}

}

