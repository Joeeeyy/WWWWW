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
import com.jjoey.walpy.adapters.ScifiWallpaperAdapter;
import com.jjoey.walpy.adapters.SeasonsAdapter;
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
public class SeasonsFragment extends Fragment {

    private static final String TAG = SeasonsFragment.class.getSimpleName();

    private RecyclerView seasonsRV;
    private List<Object> itemsList = new ArrayList<>();
    private SeasonsAdapter adapter;

    private static final int PAGE_INDEX = 1;
    private static final int PER_PAGE = 20;

    public SeasonsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View sv = inflater.inflate(R.layout.fragment_seasons, container, false);

        initView(sv);

        adapter = new SeasonsAdapter(getActivity(), itemsList);
        seasonsRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return sv;
    }

    private void initView(View sv) {
        seasonsRV = sv.findViewById(R.id.seasonsRV);

        setUpRV();

    }

    private void setUpRV() {
        seasonsRV.setHasFixedSize(true);
        seasonsRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (Utils.isNetwork(getActivity())){
            fetchWallpapers();
        } else {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Check Connection", Snackbar.LENGTH_LONG).show();
        }

    }

    private void fetchWallpapers() {
        String url = Constants.SEARCH_URL + "seasons&page="+ PAGE_INDEX + "per_page=" + PER_PAGE;
//        String url = Constants.SEARCH_URL + "seasons&per_page=" + PER_PAGE;
        Log.d(TAG, "Seasons URL:\t" + url);

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

                                    itemsList.add(images);
                                    adapter.notifyDataSetChanged();

                                    Log.d(TAG, "List Size:\t" + itemsList.size());

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
