package com.jjoey.walpy.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "favoritee")
public class Favorites extends Model{

    @Column(name = "favrites_id")
    public String favoritesId;

    @Column(name = "image_id")
    public int imageId;

    @Column(name = "large_img_url")
    public String largeImgURL;

    @Column(name = "preview_img")
    public String previewImgURL;

    public Favorites() { }

    public Favorites(String favoritesId, int imageId, String largeImgURL, String previewImgURL) {
        this.favoritesId = favoritesId;
        this.imageId = imageId;
        this.largeImgURL = largeImgURL;
        this.previewImgURL = previewImgURL;
    }

    public String getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(String favoritesId) {
        this.favoritesId = favoritesId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
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
}
