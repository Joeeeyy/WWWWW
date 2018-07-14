package com.jjoey.walpy.extras;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.androidnetworking.AndroidNetworking;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class WalpyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(this);

        ActiveAndroid.initialize(this);

//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
//
//        Picasso picasso = builder.build();
//        picasso.setIndicatorsEnabled(true);
//        picasso.setLoggingEnabled(true);
//
//        Picasso.setSingletonInstance(picasso);

    }

}
