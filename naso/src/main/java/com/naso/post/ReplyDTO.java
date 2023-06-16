package com.naso.post;
import java.sql.Timestamp;

public class ReplyDTO {
    private String rNum;
    private String pNum;
    private String rUserId;
    private String rContent;
    private Timestamp rDatetimeCreated;

    public ReplyDTO() {
    	
    }
    
    public ReplyDTO(String rNum, String pNum, String rUserId, String rContent, Timestamp rDatetimeCreated) {
        this.rNum = rNum;
        this.pNum = pNum;
        this.rUserId = rUserId;
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

    public String getRUserId() {
        return rUserId;
    }

    public void setRUserId(String rUserId) {
        this.rUserId = rUserId;
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
