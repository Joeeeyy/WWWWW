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
public class SciFiFragment extends Fragment {

    private static final String TAG = SciFiFragment.class.getSimpleName();

    private RecyclerView scifiRV;

    private List<Object> objectList = new ArrayList<>();
    private ScifiWallpaperAdapter adapter;

    private static final int PAGE_INDEX = 1;
    private static final int PER_PAGE = 20;

    public SciFiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View sv = inflater.inflate(R.layout.fragment_sci_fi, container, false);

        scifiRV = sv.findViewById(R.id.scifiRV);
        scifiRV.setHasFixedSize(true);
        scifiRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (Utils.isNetwork(getActivity())){
            fetchWallpapers();
        } else {
            // TODO: 6/12/2018 get from cache
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Check Connection", Snackbar.LENGTH_LONG).show();
        }

        adapter = new ScifiWallpaperAdapter(getActivity(), objectList);
        scifiRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return sv;
    }

    @Override
    public void onPause() {
        super.onPause();
        objectList.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        objectList.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        objectList.clear();
    }

    private void fetchWallpapers() {
        String url = Constants.SEARCH_URL + "scifi&page="+ PAGE_INDEX + "per_page=" + PER_PAGE;
//        String s = Constants.SEARCH_URL + "scifi&per_page=" + PER_PAGE;
        Log.d(TAG, "Unsplash Scifi URL:\t" + url);

        AndroidNetworking.get(url)
                .addHeaders("Accept-Version", "v1")
                .addHeaders("CLIENT-ID", Constants.UNSPLASH_ACCESS_KEY)
                .setTag("Unsplash Scifi req")
                .setPriority(Priority.HIGH)
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
