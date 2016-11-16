package com.hgt.domain;

import java.util.Date;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:照片墙精简信息类，共11个属性
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class PhotosInfoSimple {

    private String photoId;

    private String photoThumbPath;
    private String photoOriginalPath;

    private String photoTitle;


    private String photoDesc;

    private String photoTags;

    private String uploadBy;

    private Date uploadDate;

    //moduleId指的是系统模块名
    private String moduleId;

    //itemId对照片墙模块指一张照片，对文章期次型模块的指一期的文章
    private String itemId;

    private Integer remarksNum;

    private Integer likesNum;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoThumbPath() {
        return photoThumbPath;
    }

    public void setPhotoThumbPath(String photoThumbPath) {
        this.photoThumbPath = photoThumbPath;
    }

    public String getPhotoOriginalPath() {
        return photoOriginalPath;
    }

    public void setPhotoOriginalPath(String photoOriginalPath) {
        this.photoOriginalPath = photoOriginalPath;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc;
    }

    public String getPhotoTags() {
        return photoTags;
    }

    public void setPhotoTags(String photoTags) {
        this.photoTags = photoTags;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getRemarksNum() {
        return remarksNum;
    }

    public void setRemarksNum(Integer remarksNum) {
        this.remarksNum = remarksNum;
    }

    public Integer getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Integer likesNum) {
        this.likesNum = likesNum;
    }
}