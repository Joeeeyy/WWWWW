package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoey.walpy.R;
import com.jjoey.walpy.utils.RoundedCornerImageView;

public class WallpaperItemViewHolder extends RecyclerView.ViewHolder {

    public TextView setWallPaper_TV;
    public ImageView  shareWP_IV, favoriteWP_IV;
    public RoundedCornerImageView wallpaperItemImg;

    public WallpaperItemViewHolder(View itemView) {
        super(itemView);

        setWallPaper_TV = itemView.findViewById(R.id.setWallPaper_TV);
        wallpaperItemImg = itemView.findViewById(R.id.wallpaperItemImg);
        shareWP_IV = itemView.findViewById(R.id.shareWP_IV);
        favoriteWP_IV = itemView.findViewById(R.id.favoriteWP_IV);

    }
}
