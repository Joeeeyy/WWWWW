package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jjoey.walpy.R;

public class ItemsDrawerViewHolder extends RecyclerView.ViewHolder {

    public ImageView itemIconImg;

    public ItemsDrawerViewHolder(View itemView) {
        super(itemView);

        itemIconImg = itemView.findViewById(R.id.itemIconImg);

    }
}
