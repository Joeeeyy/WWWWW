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
import com.jjoey.walpy.models.PixaImages;
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
    private PixaImages pixaImages;

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

        return nv;
    }

    private void fetchWallpapers() {
        String url = Constants.PIXABAY_BASE_URL + "?key=" + Constants.PIX_API_KEY + "&q=nature&orientation=vertical&category=nature";
        Log.d(TAG, "Nature URL:\t" + url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .setTag("Get Nature Wps")
//                .getResponseOnlyIfCached()
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null){
                            Log.d(TAG, "Nature Wps Response:\t" + response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                JSONArray array = jsonObject.getJSONArray("hits");
                                for (int m = 0; m < array.length(); m++){
                                    JSONObject items = array.getJSONObject(m);

                                    pixaImages = new PixaImages();
                                    pixaImages.setImgId(items.getInt("id"));
                                    pixaImages.setLargeImgURL(items.getString("largeImageURL"));
                                    pixaImages.setPageURL(items.getString("pageURL"));
                                    pixaImages.setPreviewImgURL(items.getString("previewURL"));

                                    objectList.add(pixaImages);
                                    Log.d(TAG, "List size:\t" + objectList.size());
                                    wallpaperAdapter = new NatureWallpaperAdapter(getActivity(), objectList);
                                    natureRV.setAdapter(wallpaperAdapter);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "Failure NATURE Wps:\t" + anError.getMessage().toString());
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        objectList.clear();
        objectList.remove(pixaImages);
        Log.d(TAG, "onPause---Size:\t" + objectList.size());
    }

    @Override
    public void onStop() {
        super.onStop();
        objectList.clear();
        objectList.remove(pixaImages);
        Log.d(TAG, "onStop---Size:\t" + objectList.size());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        objectList.clear();
        objectList.remove(pixaImages);
        Log.d(TAG, "onDestroy---Size:\t" + objectList.size());
    }

}
