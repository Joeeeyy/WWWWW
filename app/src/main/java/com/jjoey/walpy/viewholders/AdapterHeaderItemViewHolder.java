package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoey.walpy.R;

public class AdapterHeaderItemViewHolder extends RecyclerView.ViewHolder {

    public TextView headerTitleTV;
    public ImageView headerIconIV;

    public AdapterHeaderItemViewHolder(View itemView) {
        super(itemView);

        headerTitleTV = itemView.findViewById(R.id.headerTitleTV);
        headerIconIV = itemView.findViewById(R.id.headerIconIV);

    }

}
