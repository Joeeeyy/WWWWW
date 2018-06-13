package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jjoey.walpy.R;
import com.jjoey.walpy.utils.RoundedCornerImageView;

public class FavoritesViewHolder extends RecyclerView.ViewHolder {

    public RoundedCornerImageView favoriteWPImg;
    public TextView removeTV, previewTV;

    public FavoritesViewHolder(View itemView) {
        super(itemView);

        favoriteWPImg = itemView.findViewById(R.id.favoriteWPImg);
        removeTV = itemView.findViewById(R.id.removeTV);
        previewTV = itemView.findViewById(R.id.previewTV);

    }
}
