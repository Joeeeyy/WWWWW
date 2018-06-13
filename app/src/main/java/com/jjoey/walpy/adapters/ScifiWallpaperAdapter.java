package com.jjoey.walpy.adapters;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoey.walpy.R;
import com.jjoey.walpy.models.Favorites;
import com.jjoey.walpy.models.PixaImages;
import com.jjoey.walpy.viewholders.WallpaperItemViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScifiWallpaperAdapter extends RecyclerView.Adapter<WallpaperItemViewHolder> {

    private static final String TAG = ScifiWallpaperAdapter.class.getSimpleName();

    private final Context context;
    private List<Object> itemsList;
    private PixaImages pixaImages;

    public ScifiWallpaperAdapter(Context context, List<Object> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public WallpaperItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_items_layout, parent, false);
        return new WallpaperItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WallpaperItemViewHolder viewholder, final int position) {
        pixaImages = (PixaImages) itemsList.get(position);

        Picasso.with(context)
                .load(pixaImages.getLargeImgURL())
                .placeholder(R.drawable.drawer_header_trimmed)
                .into(viewholder.wallpaperItemImg);

        viewholder.favoriteWP_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Favorites favorites = new Favorites();
                favorites.setFavoritesId(UUID.randomUUID().toString());
                favorites.setLargeImgURL(pixaImages.getLargeImgURL());
                favorites.setPreviewImgURL(pixaImages.getPreviewImgURL());

                List<Integer> favoritesList = new ArrayList<>();
                favoritesList.add(((PixaImages) itemsList.get(position)).getImgId());

                if (favoritesList.contains(favorites.getImageId())){
                    Toast.makeText(context, "This Wallpaper Has Been Favorited", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Duplicate Id in Favorites:\t" + favorites.getImageId());
                    Log.d(TAG, "Duplicate FavId in RecyclerList:\t" + ((PixaImages) itemsList.get(position)).getImgId());
                } else {
                    favorites.setImageId(favorites.getImageId());
                    favorites.save();
                    Log.d(TAG, "Favorites Id:\t" + favorites.getImageId());
                }

                // TODO: 6/13/2018 Fix Duplicate Favorite WP
                Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();

            }
        });

        viewholder.setWallPaper_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final WallpaperManager wpm = WallpaperManager.getInstance(context);
                Picasso.with(context)
                        .load(((PixaImages) itemsList.get(position)).getLargeImgURL())
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

    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        }
        return itemsList.size();
    }
}