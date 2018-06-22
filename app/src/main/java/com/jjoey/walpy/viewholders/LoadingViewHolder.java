package com.jjoey.walpy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jjoey.walpy.R;

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar mProgressBar;
    public ImageButton mRetryBtn;
    public TextView mErrorTxt;
    public LinearLayout mErrorLayout;

    public LoadingViewHolder(View itemView) {
        super(itemView);

        mProgressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progress);
        mRetryBtn = (ImageButton) itemView.findViewById(R.id.loadmore_retry);
        mErrorTxt = (TextView) itemView.findViewById(R.id.loadmore_errortxt);
        mErrorLayout = (LinearLayout) itemView.findViewById(R.id.loadmore_errorlayout);

//        mRetryBtn.setOnClickListener(this);
//        mErrorLayout.setOnClickListener(this);

    }
}
