package com.jjoey.walpy.api;

import com.jjoey.walpy.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashImageApi {

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient(){
        if (retrofit == null){
            return new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.UNSPLASH_BASE_URL)
                    .build();
        }
        return retrofit;
    }

}
