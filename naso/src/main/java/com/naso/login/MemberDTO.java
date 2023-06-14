package com.naso.login;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class MemberDTO {
    private String userId;
    private String password;
    private String gender;
    private String name;
    private Timestamp datetimeCreated;
    private String email;
    private Date dateOfBirth;
    private String phone;
    private Blob profileImage;
    private String instagram;
    private String tiktok;
    private String youtube;
    private String blog;
    private String kakao;
    private String africa;

    // 생성자, getter, setter 메서드 등 필요한 코드 추가

    // 생성자
    public MemberDTO() {
    }

    // getter 및 setter 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(Timestamp datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Blob getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Blob profileImage) {
        this.profileImage = profileImage;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getKakao() {
        return kakao;
    }

    public void setKakao(String kakao) {
        this.kakao = kakao;
    }

    public String getAfrica() {
        return africa;
    }

    public void setAfrica(String africa) {
        this.africa = africa;
    }
}
