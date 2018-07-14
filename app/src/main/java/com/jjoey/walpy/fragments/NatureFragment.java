package com.jjoey.walpy.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jjoey.walpy.R;
import com.jjoey.walpy.adapters.NatureWallpaperAdapter;
import com.jjoey.walpy.models.Results;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NatureFragment extends Fragment {

    private static final String TAG = NatureFragment.class.getSimpleName();

    private RecyclerView natureRV;

    private List<Object> objectList = new ArrayList<>();
    private NatureWallpaperAdapter wallpaperAdapter;

    private static final int PAGE_INDEX = 1;
    private static final int PER_PAGE = 20;

    public NatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nv = inflater.inflate(R.layout.fragment_nature, container, false);

        natureRV = nv.findViewById(R.id.natureRV);
        natureRV.setHasFixedSize(true);
        natureRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (Utils.isNetwork(getActivity())){
            fetchWallpapers();
        } else {
            // TODO: 6/12/2018 get from cache
            Snackbar.make(nv.findViewById(android.R.id.content), "Check Connection", Snackbar.LENGTH_LONG).show();
        }

        wallpaperAdapter = new NatureWallpaperAdapter(getActivity(), objectList);
        natureRV.setAdapter(wallpaperAdapter);
        wallpaperAdapter.notifyDataSetChanged();

        return nv;
    }

    private void fetchWallpapers() {

        String url = Constants.SEARCH_URL + "nature&page="+ PAGE_INDEX + "per_page=" + PER_PAGE;
//        String url = Constants.SEARCH_URL + "nature&per_page=" + PER_PAGE;
        Log.d(TAG, "Nature Unsplash URL:\t" + url);

        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .setTag("Get Seasons Wallpapers")
                .getResponseOnlyFromNetwork()
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Un-Response:\t" + response.toString());
                        if (response != null){
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                JSONArray results = jsonObject.getJSONArray("results");

                                for (int p = 0; p < results.length(); p++){
                                    JSONObject items = results.getJSONObject(p);

                                    Results images = new Results();
                                    images.setImageId(items.getString("id"));

                                    JSONObject urls = items.getJSONObject("urls");

                                    images.setRawImg(urls.getString("raw"));
                                    images.setFullImg(urls.getString("full"));
                                    images.setRegularImg(urls.getString("regular"));
                                    images.setSmallImg(urls.getString("small"));
                                    images.setThumbImg(urls.getString("thumb"));

                                    objectList.add(images);
                                    wallpaperAdapter.notifyDataSetChanged();

                                    Log.d(TAG, "List Size:\t" + objectList.size());

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    @Override
    public void onPause() {
        super.onPause();
        objectList.clear();
        Log.d(TAG, "onPause---Size:\t" + objectList.size());
    }

    @Override
    public void onStop() {
        super.onStop();
        objectList.clear();
        Log.d(TAG, "onStop---Size:\t" + objectList.size());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        objectList.clear();
        Log.d(TAG, "onDestroy---Size:\t" + objectList.size());
    }

}
