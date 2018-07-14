package com.jjoey.walpy.fragments;

import android.os.Bundle;
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
import com.jjoey.walpy.api.ApiService;
import com.jjoey.walpy.api.UnsplashImageApi;
import com.jjoey.walpy.models.ImagesResponse;
import com.jjoey.walpy.models.Results;
import com.jjoey.walpy.utils.Constants;
import com.jjoey.walpy.utils.PaginationScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtFragment extends Fragment {

    private static final String TAG = ArtFragment.class.getSimpleName();

    private RecyclerView artRV;
    private List<Results> objectList = new ArrayList<>();
    private ArtAdapter adapter;

    private static int TOTAL;
    private static int TOTAL_PAGES;
    private boolean isLoading = false, isLastPage = false;

    private static int PAGE_START_INDEX = 1;
    private static int CURRENT_PAGE = PAGE_START_INDEX;

    private static final int PER_PAGE = 20;

    private ApiService apiService;

    public ArtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View artView = inflater.inflate(R.layout.fragment_art, container, false);

        initView(artView);

        apiService = UnsplashImageApi.getRetrofitClient().create(ApiService.class);
        loadFirstPage();

        artRV.setHasFixedSize(true);
        LinearLayoutManager vlm = new LinearLayoutManager(getActivity());
        vlm.setOrientation(LinearLayoutManager.VERTICAL);
        artRV.setLayoutManager(vlm);

        fetchWallpapers();

        adapter = new ArtAdapter(getActivity(), objectList);
        artRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        artRV.addOnScrollListener(new PaginationScrollListener(vlm) {
//            @Override
//            protected void loadMoreItems() {
//                isLoading = true;
//                CURRENT_PAGE += 1;
//                loadNextPage();
//            }
//
//            @Override
//            protected int getTotalPageCount() {
//                return TOTAL_PAGES;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//        });

        return artView;
    }

    private void loadNextPage() {
        getArtImagesResopnse().enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                Log.d(TAG, "Art Retrofit Response:\t" + response.toString());

                adapter.removeLoadingFooter();
                isLoading = false;

                List<Results> results = fetchResults(response);
                adapter.addAll(results);

                if (CURRENT_PAGE != TOTAL_PAGES) { adapter.addLoadingFooter(); }
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {

            }
        });
    }

    private void loadFirstPage() {
        getArtImagesResopnse().enqueue(new Callback<ImagesResponse>() {
            @Override
            public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                Log.d(TAG, "Art Retrofit Response:\t" + response.toString());

                List<Results> results = fetchResults(response);
                adapter.addAll(results);

                if (CURRENT_PAGE <= TOTAL_PAGES) { adapter.addLoadingFooter(); }
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<ImagesResponse> call, Throwable t) {

            }
        });
    }

    private List<Results> fetchResults(Response<ImagesResponse> response) {
        ImagesResponse imagesResponse = response.body();
        TOTAL_PAGES = imagesResponse.getTotalPages();
        Log.d(TAG, "Total pages:\t" + TOTAL_PAGES);
        return imagesResponse.getImagesList();
    }

    private Call<ImagesResponse> getArtImagesResopnse(){
        return apiService.getImages(Constants.UNSPLASH_ACCESS_KEY, "latest", "art", CURRENT_PAGE);
    }

    private void initView(View artView) {
        artRV = artView.findViewById(R.id.artRV);
    }

    private void fetchWallpapers() {
        String url = Constants.SEARCH_URL + "art&page="+ PAGE_START_INDEX;
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
                        Log.d(TAG, "ART Un-Response:\t" + response.toString());
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
