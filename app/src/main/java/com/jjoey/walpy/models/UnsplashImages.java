package com.jjoey.walpy.models;

public class UnsplashImages {

    public String imageId;
    public String rawImg;
    public String fullImg;
    public String regularImg;
    public String smallImg;
    public String thumbImg;

    public UnsplashImages() {}

    public UnsplashImages(String imageId, String rawImg, String fullImg, String regularImg, String smallImg, String thumbImg) {
        this.imageId = imageId;
        this.rawImg = rawImg;
        this.fullImg = fullImg;
        this.regularImg = regularImg;
        this.smallImg = smallImg;
        this.thumbImg = thumbImg;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getRawImg() {
        return rawImg;
    }

    public void setRawImg(String rawImg) {
        this.rawImg = rawImg;
    }

    public String getFullImg() {
        return fullImg;
    }

    public void setFullImg(String fullImg) {
        this.fullImg = fullImg;
    }

    public String getRegularImg() {
        return regularImg;
    }

    public void setRegularImg(String regularImg) {
        this.regularImg = regularImg;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }
}
