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

import com.activeandroid.query.Select;
import com.jjoey.walpy.R;
import com.jjoey.walpy.interfaces.PaginationAdapterCallback;
import com.jjoey.walpy.models.Favorites;
import com.jjoey.walpy.models.ImagesResponse;
import com.jjoey.walpy.models.Results;
import com.jjoey.walpy.viewholders.LoadingViewHolder;
import com.jjoey.walpy.viewholders.WallpaperItemViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ArtAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = ArtAdapter.class.getSimpleName();

    private static final int ITEMS = 0;
    private static final int PROGRESS = 1;

    private final Context context;
    private List<Results> itemsList;

    private Results results;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private PaginationAdapterCallback mCallback;

    private String errorMsg;

    public ArtAdapter(Context context, List<Results> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        results = itemsList.get(position);
        switch (getItemViewType(position)) {
            case ITEMS:
                Log.d(TAG, "Small URL:\t" + results.getSmallImg());
                final WallpaperItemViewHolder viewHolder = (WallpaperItemViewHolder) holder;
                Picasso.with(context)
                        .load(itemsList.get(position).getSmallImg())
                        .placeholder(R.drawable.drawer_header_trimmed)
                        .into(viewHolder.wallpaperItemImg);

                final String imageId = ((Results) itemsList.get(position)).imageId;
                Log.d(TAG, "Img id:\t" + imageId);

                // TODO: 7/7/2018 Toggle Likes Visibility

                viewHolder.favoriteWP_IV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Favorites favorites = new Favorites();
                        favorites.results = (Results) itemsList.get(position);

                        final Favorites favorites1 = new Select()
                                .from(Favorites.class)
                                .where("image_id=?", imageId)
                                .executeSingle();

                        if (favorites1 != null) {
                            viewHolder.favoriteWP_IV.setImageDrawable(context.getResources().getDrawable(R.drawable.heart_inactive));
                            results.isFavorite = false;
                        } else {
                            favorites.results.isFavorite = true;
                            viewHolder.favoriteWP_IV.setImageDrawable(context.getResources().getDrawable(R.drawable.heart_active));
                            results.isFavorite = true;

                            favorites.setFavoritesId(UUID.randomUUID().toString());
                            favorites.setImageId(favorites.getResults().getImageId());
                            favorites.setLargeImgURL(favorites.getResults().getRegularImg());
                            favorites.setPreviewImgURL(favorites.getResults().getRegularImg());
                            favorites.setSmallImgURL(favorites.getResults().getSmallImg());

                            favorites.save();
                            Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                viewHolder.setWallPaper_TV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final WallpaperManager wpm = WallpaperManager.getInstance(context);

                        Picasso.with(context)
                                .load(((Results) itemsList.get(position)).getRegularImg())
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
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                if (retryPageLoad) {
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
        if (position == 0) {
            return ITEMS;
        } else {
            return (position == itemsList.size() - 1 && isLoadingAdded) ? PROGRESS : ITEMS;
        }
    }

    public void addAll(List<Results> results) {
        for (Results images : results) {
            add(images);
        }
    }

    private void add(Results images) {
        itemsList.add(images);
        notifyItemInserted(itemsList.size() - 1);
    }

    public void remove(Results images) {
        int position = itemsList.indexOf(images);
        if (position > -1) {
            itemsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Results());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = itemsList.size() - 1;
        Results result = getItem(position);

        if (result != null) {
            itemsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    private Results getItem(int position) {
        return itemsList.get(position);
    }

    public void showRetry(boolean show, @Nullable String errorMsg){
        retryPageLoad = true;
        notifyItemChanged(itemsList.size() - 1);
        if (errorMsg != null){this.errorMsg = errorMsg; } else { errorMsg = "Can't Fetch Wallpapers Now..."; }
    }

}