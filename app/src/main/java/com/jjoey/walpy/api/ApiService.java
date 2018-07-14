package com.jjoey.walpy.api;

import com.jjoey.walpy.models.ImagesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/photos")
    Call<ImagesResponse> getImages(
            @Query("client_id") String clientId,
            @Query("order_by") String orderBy,
            @Query("query") String query,
            @Query("page") int pageIndex
    );

}
