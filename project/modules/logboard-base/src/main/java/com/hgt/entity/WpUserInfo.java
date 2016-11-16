package com.hgt.entity;

import java.util.Date;

/**
 * 数据库实体类，对应wp_user_info表，共17个属性
 */
public class WpUserInfo {

    private String userId;

    private String userName;

    private String userPwdHash;

    private String userPwdSalt;

    private String userNickname;

    private Integer userAge;

    private String userTel;

    private String userTelValidated;

    private String userEmail;

    private String userEmailValidated;

    private String userAvatar;

    private Integer utypeId;

    private Date utypeUpdateDate;

    private String utypeUpdateBy;

    private Integer userRemarksNum;

    private Integer userLikesNum;

    private Integer userFavesNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwdHash() {
        return userPwdHash;
    }

    public void setUserPwdHash(String userPwdHash) {
        this.userPwdHash = userPwdHash == null ? null : userPwdHash.trim();
    }

    public String getUserPwdSalt() {
        return userPwdSalt;
    }

    public void setUserPwdSalt(String userPwdSalt) {
        this.userPwdSalt = userPwdSalt == null ? null : userPwdSalt.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getUserTelValidated() {
        return userTelValidated;
    }

    public void setUserTelValidated(String userTelValidated) {
        this.userTelValidated = userTelValidated == null ? null : userTelValidated.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserEmailValidated() {
        return userEmailValidated;
    }

    public void setUserEmailValidated(String userEmailValidated) {
        this.userEmailValidated = userEmailValidated == null ? null : userEmailValidated.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public Integer getUtypeId() {
        return utypeId;
    }

    public void setUtypeId(Integer utypeId) {
        this.utypeId = utypeId;
    }

    public Date getUtypeUpdateDate() {
        return utypeUpdateDate;
    }

    public void setUtypeUpdateDate(Date utypeUpdateDate) {
        this.utypeUpdateDate = utypeUpdateDate;
    }

    public String getUtypeUpdateBy() {
        return utypeUpdateBy;
    }

    public void setUtypeUpdateBy(String utypeUpdateBy) {
        this.utypeUpdateBy = utypeUpdateBy == null ? null : utypeUpdateBy.trim();
    }

    public Integer getUserRemarksNum() {
        return userRemarksNum;
    }

    public void setUserRemarksNum(Integer userRemarksNum) {
        this.userRemarksNum = userRemarksNum;
    }

    public Integer getUserLikesNum() {
        return userLikesNum;
    }

    public void setUserLikesNum(Integer userLikesNum) {
        this.userLikesNum = userLikesNum;
    }

    public Integer getUserFavesNum() {
        return userFavesNum;
    }

    public void setUserFavesNum(Integer userFavesNum) {
        this.userFavesNum = userFavesNum;
    }
}