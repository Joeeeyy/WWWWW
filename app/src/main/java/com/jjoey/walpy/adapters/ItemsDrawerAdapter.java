package com.jjoey.walpy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoey.walpy.R;
import com.jjoey.walpy.models.ItemsDrawer;
import com.jjoey.walpy.models.ItemsHeader;
import com.jjoey.walpy.viewholders.DrawerHeaderItemViewHolder;
import com.jjoey.walpy.viewholders.ItemsDrawerViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemsDrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private List<Object> itemsList;

    public static final int HEADER_VIEW = 0;
    public static final int ITEMS_VIEW = 1;

    public ItemsDrawerAdapter(Context context, List<Object> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_drawer_layout, parent, false);
            return new DrawerHeaderItemViewHolder(view);
        } else if (viewType == ITEMS_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_drawer_layout, parent, false);
            return new ItemsDrawerViewHolder(view);
        }
        throw new RuntimeException("No matching viewTypes");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DrawerHeaderItemViewHolder) {
            ItemsHeader header = (ItemsHeader) itemsList.get(position);
            Picasso.with(context)
                    .load(header.getHeaderIcon())
                    .into(((DrawerHeaderItemViewHolder) holder).drawerHeaderIconImg);

        } else if (holder instanceof ItemsDrawerViewHolder) {
            ItemsDrawer items = (ItemsDrawer) itemsList.get(position);
            Picasso.with(context)
                    .load(items.getIcon())
                    .into(((ItemsDrawerViewHolder) holder).itemIconImg);
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return HEADER_VIEW;
        return ITEMS_VIEW;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}