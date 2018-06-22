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
import com.jjoey.walpy.adapters.ArtAdapter;
import com.jjoey.walpy.adapters.SpaceAdapter;
import com.jjoey.walpy.models.UnsplashImages;
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
public class ArtFragment extends Fragment {

    private static final String TAG = ArtFragment.class.getSimpleName();

    private RecyclerView artRV;
    private List<Object> objectList = new ArrayList<>();
    private ArtAdapter adapter;

    private static final int PAGE_INDEX = 1;
    private static final int PER_PAGE = 20;

    public ArtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View artView = inflater.inflate(R.layout.fragment_art, container, false);

        initView(artView);

        if (Utils.isNetwork(getActivity())){
            fetchWallpapers();
        } else {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Check Connection", Snackbar.LENGTH_LONG).show();
        }

        adapter = new ArtAdapter(getActivity(), objectList);
        artRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return artView;
    }

    private void initView(View artView) {
        artRV = artView.findViewById(R.id.artRV);

        setUpRV();

    }

    private void setUpRV() {
        artRV.setHasFixedSize(true);
        artRV.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void fetchWallpapers() {
        String url = Constants.SEARCH_URL + "art&page="+ PAGE_INDEX + "per_page=" + PER_PAGE;
//        String url = Constants.SEARCH_URL + "art&per_page=" + PER_PAGE;
        Log.d(TAG, "ART URL:\t" + url);

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

                                    UnsplashImages images = new UnsplashImages();
                                    images.setImageId(items.getString("id"));

                                    JSONObject urls = items.getJSONObject("urls");

                                    images.setRawImg(urls.getString("raw"));
                                    images.setFullImg(urls.getString("full"));
                                    images.setRegularImg(urls.getString("regular"));
                                    images.setSmallImg(urls.getString("small"));
                                    images.setThumbImg(urls.getString("thumb"));

                                    objectList.add(images);
                                    adapter.notifyDataSetChanged();

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

}
