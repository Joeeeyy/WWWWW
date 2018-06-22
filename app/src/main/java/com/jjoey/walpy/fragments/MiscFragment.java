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
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jjoey.walpy.R;
import com.jjoey.walpy.adapters.MiscAdapter;
import com.jjoey.walpy.adapters.ScifiWallpaperAdapter;
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
public class MiscFragment extends Fragment {

    private static final String TAG = MiscFragment.class.getSimpleName();

    private RecyclerView miscRV;
    private EditText searchWP_et;

    private List<Object> objectList = new ArrayList<>();
    private MiscAdapter adapter;

    private static final int PAGE_INDEX = 1;
    private static final int PER_PAGE = 20;

    public MiscFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_misc, container, false);

        intViews(v);

        return v;
    }

    private void intViews(View v) {
        searchWP_et = v.findViewById(R.id.searchWP_et);
        miscRV = v.findViewById(R.id.miscRV);

        setUpRV();

    }

    private void setUpRV() {
        miscRV.setHasFixedSize(true);
        miscRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (Utils.isNetwork(getActivity())) {
            fetchWallpapers();
        } else {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Check Connection", Snackbar.LENGTH_LONG).show();
        }

        adapter = new MiscAdapter(getActivity(), objectList);
        miscRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void fetchWallpapers() {
//        String url = Constants.SEARCH_URL + "top30&page="+ PAGE_INDEX + "per_page=" + PER_PAGE;
        String s = Constants.RANDOM_PHOTOS + "&count=30";
        Log.d(TAG, "Unsplash Misc URL:\t" + s);

        AndroidNetworking.get(s)
                .addHeaders("Accept-Version", "v1")
                .addHeaders("CLIENT-ID", Constants.UNSPLASH_ACCESS_KEY)
                .setTag("Unsplash Scifi req")
                .setPriority(Priority.HIGH)
                .getResponseOnlyFromNetwork()
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "Un-Response:\t" + response.toString());
                        if (response != null) {
                            try {
                                JSONArray jsonArray = new JSONArray(response.toString());

                                for (int m = 0; m < jsonArray.length(); m++) {
                                    JSONObject object = jsonArray.getJSONObject(m);

                                    UnsplashImages images = new UnsplashImages();
                                    images.setImageId(object.getString("id"));

                                    JSONObject urls = object.getJSONObject("urls");

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
