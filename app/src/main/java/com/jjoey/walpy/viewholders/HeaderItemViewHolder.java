package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jjoey.walpy.R;

public class HeaderItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView headerIconImg;

    public HeaderItemViewHolder(View itemView) {
        super(itemView);

        headerIconImg = itemView.findViewById(R.id.headerIconImg);

    }
}
