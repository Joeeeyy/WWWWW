package com.jjoey.walpy.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "favorites")
public class Favorites extends Model{

    @Column(name = "favorites_id")
    public String favoritesId;

    @Column(name = "image_id")
    public String imageId;

    @Column(name = "large_img_url")
    public String largeImgURL;

    @Column(name = "preview_img")
    public String previewImgURL;

    @Column(name = "small_img")
    public String smallImgURL;

    public Results results;

    public Favorites() { }

    public Favorites(String favoritesId, String imageId, String largeImgURL, String previewImgURL, String smallImgURL, Results results) {
        this.favoritesId = favoritesId;
        this.imageId = imageId;
        this.largeImgURL = largeImgURL;
        this.previewImgURL = previewImgURL;
        this.smallImgURL = smallImgURL;
        this.results = results;
    }

    public String getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(String favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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

    public String getSmallImgURL() {
        return smallImgURL;
    }

    public void setSmallImgURL(String smallImgURL) {
        this.smallImgURL = smallImgURL;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
