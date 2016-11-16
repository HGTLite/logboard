package com.hgt.entity;

import java.util.Date;

/**
 * 数据库实体类，对应wp_photos_info表，共19个属性
 */
public class WpPhotosInfo {

    private String photoId;

    private String photoThumbPath;

    private String photoOriginalPath;

    private String photoTitle;

    private String photoDesc;

    private String photoTags;

    private String uploadBy;

    private Date uploadDate;

    private String isPublic;

    private String isDeleted;

    private String moduleId;

    private String itemId;

    private Integer remarksNum;

    private Integer likesNum;

    private Integer favesNum;

    private String creator;

    private Date createTime;

    private String paddress;

    private String plonlat;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId == null ? null : photoId.trim();
    }

    public String getPhotoThumbPath() {
        return photoThumbPath;
    }

    public void setPhotoThumbPath(String photoThumbPath) {
        this.photoThumbPath = photoThumbPath == null ? null : photoThumbPath.trim();
    }

    public String getPhotoOriginalPath() {
        return photoOriginalPath;
    }

    public void setPhotoOriginalPath(String photoOriginalPath) {
        this.photoOriginalPath = photoOriginalPath == null ? null : photoOriginalPath.trim();
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle == null ? null : photoTitle.trim();
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc == null ? null : photoDesc.trim();
    }

    public String getPhotoTags() {
        return photoTags;
    }

    public void setPhotoTags(String photoTags) {
        this.photoTags = photoTags == null ? null : photoTags.trim();
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy == null ? null : uploadBy.trim();
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
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

    public Integer getFavesNum() {
        return favesNum;
    }

    public void setFavesNum(Integer favesNum) {
        this.favesNum = favesNum;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress == null ? null : paddress.trim();
    }

    public String getPlonlat() {
        return plonlat;
    }

    public void setPlonlat(String plonlat) {
        this.plonlat = plonlat == null ? null : plonlat.trim();
    }
}