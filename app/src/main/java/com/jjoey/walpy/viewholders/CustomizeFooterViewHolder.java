package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jjoey.walpy.R;

public class CustomizeFooterViewHolder extends RecyclerView.ViewHolder{

    public Button letsGoBtn;

    public CustomizeFooterViewHolder(View itemView) {
        super(itemView);

        letsGoBtn = itemView.findViewById(R.id.letsGoBtn);

    }
}
