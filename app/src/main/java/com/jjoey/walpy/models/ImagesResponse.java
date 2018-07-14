package com.jjoey.walpy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImagesResponse {

    @Expose
    @SerializedName("total")
    private Integer total;

    @Expose
    @SerializedName("total_pages")
    private Integer totalPages;

    @Expose
    @SerializedName("results")
    private List<Results> imagesList = new ArrayList<>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Results> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Results> imagesList) {
        this.imagesList = imagesList;
    }
}
