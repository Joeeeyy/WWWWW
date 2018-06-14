package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jjoey.walpy.R;

public class PrefsCustomizeViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = PrefsCustomizeViewHolder.class.getSimpleName();

    public CheckBox prefsCheckBox;
    public ImageView prefsSelImg;
    public RelativeLayout prefsRowLayout;

    public int height, width;

    public PrefsCustomizeViewHolder(View itemView) {
        super(itemView);

        prefsCheckBox = itemView.findViewById(R.id.prefsCheckBox);
        prefsSelImg = itemView.findViewById(R.id.prefsSelImg);
        prefsRowLayout = itemView.findViewById(R.id.prefsRowLayout);

    }
}
