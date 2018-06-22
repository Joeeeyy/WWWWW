package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jjoey.walpy.R;

public class PrefsCustomizeFooterViewHolder extends RecyclerView.ViewHolder {

    public Button doneBtn;

    public PrefsCustomizeFooterViewHolder(View itemView) {
        super(itemView);

        doneBtn = itemView.findViewById(R.id.prefs_doneBtn);

    }
}
