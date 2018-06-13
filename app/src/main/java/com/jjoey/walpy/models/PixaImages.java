package com.jjoey.walpy.models;

public class PixaImages {

    public int imgId;
    public String largeImgURL;
    public String previewImgURL;
    public String pageURL;

    public PixaImages() {
    }

    public PixaImages(int imgId, String largeImgURL, String previewImgURL, String pageURL) {
        this.imgId = imgId;
        this.largeImgURL = largeImgURL;
        this.previewImgURL = previewImgURL;
        this.pageURL = pageURL;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getLargeImgURL() {
        return largeImgURL;
    }

    public void setLargeImgURL(String largeImgURL) {
        this.largeImgURL = largeImgURL;
    }

    public String getPreviewImgURL() {
        return previewImgURL;
    }

    public void setPreviewImgURL(String previewImgURL) {
        this.previewImgURL = previewImgURL;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }
}
