package com.jjoey.walpy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjoey.walpy.PreviewWallPaperActivity;
import com.jjoey.walpy.R;
import com.jjoey.walpy.models.Favorites;
import com.jjoey.walpy.viewholders.FavoritesViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private static final String TAG = FavoritesAdapter.class.getSimpleName();

    private final Context context;
    private List<Favorites> itemsList;

    public FavoritesAdapter(Context context, List<Favorites> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item_layout, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder viewholder, final int position) {
        final Favorites favorites = itemsList.get(position);

        Picasso.with(context)
                .load(favorites.getLargeImgURL())
                .placeholder(R.drawable.drawer_header_trimmed)
                .into(viewholder.favoriteWPImg);

        viewholder.removeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = favorites.getId();
                removeAtPosition(position, id);
            }
        });

        viewholder.previewTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previewIntent = new Intent(context, PreviewWallPaperActivity.class);
                previewIntent.putExtra("fav_id", favorites.getFavoritesId());
                previewIntent.putExtra("large_url", favorites.getLargeImgURL());
                previewIntent.putExtra("preview_url", favorites.getPreviewImgURL());
                context.startActivity(previewIntent);
            }
        });
    }

    private void removeAtPosition(int position, long id) {
        Favorites.delete(Favorites.class, id);
        itemsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemsList.size());

        Toast.makeText(context, "Removed From Favorites", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        }
        return itemsList.size();
    }
}