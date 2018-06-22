package com.jjoey.walpy.adapters;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoey.walpy.R;
import com.jjoey.walpy.models.Favorites;
import com.jjoey.walpy.models.UnsplashImages;
import com.jjoey.walpy.viewholders.LoadingViewHolder;
import com.jjoey.walpy.viewholders.WallpaperItemViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Top30Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = Top30Adapter.class.getSimpleName();

    private final Context context;
    private List<UnsplashImages> itemsList;

    private UnsplashImages unsplashImages;

    private static final int ITEMS = 0;
    private static final int PROGRESS = 1;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private String errorMsg;

//    private PaginationAdapterCallback mCallback;

    public Top30Adapter(Context context, List<UnsplashImages> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ITEMS:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_items_layout, parent, false);
                return new WallpaperItemViewHolder(view);
            case PROGRESS:
                View pv = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_items_layout, parent, false);
                return new LoadingViewHolder(pv);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewholder, final int position) {
        switch (getItemViewType(position)){
            case ITEMS:
                WallpaperItemViewHolder wallpaperItemViewHolder = (WallpaperItemViewHolder) viewholder;
                unsplashImages = (UnsplashImages) itemsList.get(position);
                Picasso.with(context)
                        .load(unsplashImages.getRegularImg())
                        .placeholder(R.drawable.drawer_header_trimmed)
                        .into(wallpaperItemViewHolder.wallpaperItemImg);

                wallpaperItemViewHolder.favoriteWP_IV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Favorites favorites = new Favorites();
                        favorites.setFavoritesId(UUID.randomUUID().toString());
                        favorites.setLargeImgURL(unsplashImages.getRegularImg());
                        favorites.setPreviewImgURL(unsplashImages.getRegularImg());
                        favorites.save();
                        Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();

                    }
                });

                wallpaperItemViewHolder.setWallPaper_TV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final WallpaperManager wpm = WallpaperManager.getInstance(context);

                        Picasso.with(context)
                                .load(((UnsplashImages) itemsList.get(position)).getRegularImg())
                                .into(new Target() {
                                    @Override
                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                        try {
                                            wpm.setBitmap(bitmap);
                                            Toast.makeText(context, "Your New Wallpaper Has Been Set", Toast.LENGTH_SHORT).show();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onBitmapFailed(Drawable errorDrawable) {
                                        Log.d(TAG, "Bitmap Load Failed");
                                        Toast.makeText(context, "Could Not Set Wallpaper...Choose Another", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                                        Log.d(TAG, "Prep to Load Bitmap");
                                    }
                                });

                    }
                });
                break;
            case PROGRESS:
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewholder;
                if (retryPageLoad){
                    loadingViewHolder.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingViewHolder.mProgressBar.setVisibility(View.GONE);

                    loadingViewHolder.mErrorTxt.setText(errorMsg != null
                            ? errorMsg
                            : context.getString(R.string.error_msg_unknown));

                } else {
                    loadingViewHolder.mErrorLayout.setVisibility(View.GONE);
                    loadingViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        }
        return itemsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == itemsList.size() - 1 && isLoadingAdded) ? PROGRESS : ITEMS;
    }

    public void addImage(UnsplashImages images){
        itemsList.add(images);
        notifyItemInserted(itemsList.size() - 1);
    }

    public void addAllImages(List<UnsplashImages> list){
        for (UnsplashImages images : list){
            addImage(images);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        addImage(new UnsplashImages());
    }

    public void removeLoadingFooter(){
        isLoadingAdded = false;

        int position = itemsList.size() - 1;
        UnsplashImages unsplashImages = getItem(position);

        if (unsplashImages != null){
            itemsList.remove(unsplashImages);
            notifyItemRemoved(position);
        }

    }

    private UnsplashImages getItem(int position) {
        return itemsList.get(position);
    }

    public void showRetry(boolean show, @Nullable String errorMsg){
        retryPageLoad = true;
        notifyItemChanged(itemsList.size() - 1);
        if (errorMsg != null){this.errorMsg = errorMsg; } else { errorMsg = "Can't Fetch Wallpapers Now..."; }
    }

}