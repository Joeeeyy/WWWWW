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
import com.jjoey.walpy.adapters.Top30Adapter;
import com.jjoey.walpy.models.Results;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.utils.PaginationScrollListener;
import com.jjoey.walpy.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top30Fragment extends Fragment {

    private static final String TAG = Top30Fragment.class.getSimpleName();

    private RecyclerView top30RV;

    private List<Object> itemsList = new ArrayList<>();
    private Top30Adapter adapter;

    private int PER_PAGE = 30;
    private String url = Constants.SEARCH_URL + "top30&per_page=" + PER_PAGE;

    public Top30Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vtop = inflater.inflate(R.layout.fragment_top30, container, false);

        top30RV = vtop.findViewById(R.id.top30RV);
        top30RV.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        top30RV.setLayoutManager(llm);

            fetchWallpapers();
//        if (Utils.isNetwork(getActivity())) {
//        } else {
//            Snackbar.make(getActivity().findViewById(android.R.id.content), "Check Connection", Snackbar.LENGTH_LONG).show();
//        }

        adapter = new Top30Adapter(getActivity(), itemsList);
        top30RV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return vtop;
    }

    private void fetchWallpapers() {
        Log.d(TAG, "Top30 Unsplash URL:\t" + url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .setTag("Get Seasons Wallpapers")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Un-Response:\t" + response.toString());
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                JSONArray results = jsonObject.getJSONArray("results");

                                for (int p = 0; p < results.length(); p++) {
                                    JSONObject items = results.getJSONObject(p);

                                    Results images = new Results();
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
