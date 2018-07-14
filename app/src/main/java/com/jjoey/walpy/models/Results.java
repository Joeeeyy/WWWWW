package com.jjoey.walpy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @Expose
    @SerializedName("id")
    public String imageId;

    @Expose
    @SerializedName("raw")
    public String rawImg;

    @Expose
    @SerializedName("full")
    public String fullImg;

    @Expose
    @SerializedName("regular")
    public String regularImg;

    @Expose
    @SerializedName("small")
    public String smallImg;

    @Expose
    @SerializedName("thumb")
    public String thumbImg;

    public boolean isFavorite;

    public Results() {}

    public Results(String imageId, String rawImg, String fullImg, String regularImg, String smallImg, String thumbImg, boolean isFavorite) {
        this.imageId = imageId;
        this.rawImg = rawImg;
        this.fullImg = fullImg;
        this.regularImg = regularImg;
        this.smallImg = smallImg;
        this.thumbImg = thumbImg;
        this.isFavorite = isFavorite;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
