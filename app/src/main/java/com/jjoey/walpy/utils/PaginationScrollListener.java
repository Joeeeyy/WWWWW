package com.jjoey.walpy.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager llm;

    public PaginationScrollListener(LinearLayoutManager llm) {
        this.llm = llm;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visisbleItemCount = recyclerView.getChildCount();
        int totalItemCount = llm.getItemCount();
        int firstVisibleItemPosn = llm.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()){
            if ((visisbleItemCount + firstVisibleItemPosn) >= totalItemCount && firstVisibleItemPosn > 0){
                loadMoreItems();
            }
        }

    }

    protected abstract void loadMoreItems();

    protected abstract int getTotalPageCount();

    public abstract boolean isLoading();

    public abstract boolean isLastPage();

}
