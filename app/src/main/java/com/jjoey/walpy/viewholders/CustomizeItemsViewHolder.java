package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jjoey.walpy.R;

public class CustomizeItemsViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = CustomizeItemsViewHolder.class.getSimpleName();

    public CheckBox customiseCheckBox;
    public ImageView customizeSelImg;
    public RelativeLayout rowLayout;

    public int height, width;

    public CustomizeItemsViewHolder(View itemView) {
        super(itemView);

        customiseCheckBox = itemView.findViewById(R.id.customiseCheckBox);
        customizeSelImg = itemView.findViewById(R.id.customizeSelImg);
        rowLayout = itemView.findViewById(R.id.rowLayout);

    }
}
